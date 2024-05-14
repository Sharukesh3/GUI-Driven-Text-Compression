import heapq
import matplotlib.pyplot as plt
import networkx as nx

class HuffmanNode:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, other):
        return self.freq < other.freq

def build_huffman_tree(text):
    freq = {}
    for char in text:
        freq[char] = freq.get(char, 0) + 1

    priority_queue = [HuffmanNode(char, freq) for char, freq in freq.items()]
    heapq.heapify(priority_queue)

    while len(priority_queue) > 1:
        left = heapq.heappop(priority_queue)
        right = heapq.heappop(priority_queue)

        merged = HuffmanNode(None, left.freq + right.freq)
        merged.left = left
        merged.right = right

        heapq.heappush(priority_queue, merged)

    return priority_queue[0]

def generate_huffman_codes(node, code='', codes={}):
    if node:
        if node.char is not None:
            codes[node.char] = code
        generate_huffman_codes(node.left, code + '0', codes)
        generate_huffman_codes(node.right, code + '1', codes)
    return codes

def position_tree(root, x, y, pos, depth=0, h_dist=2):
    if root:
        position_tree(root.left, x - h_dist, y - 1, pos, depth + 1, h_dist / 2)
        pos[root] = (x, y)
        position_tree(root.right, x + h_dist, y - 1, pos, depth + 1, h_dist / 2)

def visualize_huffman_tree(root):
    G = nx.Graph()
    pos = {}
    position_tree(root, 0, 0, pos)
    for node, (x, y) in pos.items():
        G.add_node(node, pos=(x, -y), label=f"{node.char}\n{node.freq}" if node.char is not None else node.freq)

    edges = [(node, node.left) for node in pos if node.left]
    edges.extend([(node, node.right) for node in pos if node.right])
    G.add_edges_from(edges)

    node_labels = nx.get_node_attributes(G, 'label')
    node_positions = nx.get_node_attributes(G, 'pos')

    nx.draw(G, pos=node_positions, labels=node_labels, with_labels=True, node_size=2000, node_color='skyblue', font_size=10)
    plt.show()

def main():
    text = input()
    root = build_huffman_tree(text)
    
    '''
    codes = generate_huffman_codes(root)
    print("Huffman Codes:")
    for char, code in sorted(codes.items()):
        print(f"{char}: {code}")
    '''
    visualize_huffman_tree(root)
    

if __name__ == "__main__":
    main()
