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

def visualize_huffman_tree(root):
    G = nx.Graph()
    node_count = 0
    G.add_node(node_count, label=f"{root.char}\n{root.freq}" if root.char is not None else root.freq)
    stack = [(root, 0, node_count)]
    pos = {node_count: (0, 0)}

    while stack:
        node, depth, parent = stack.pop()
        if node.left:
            node_count += 1
            label = f"{node.left.char}\n{node.left.freq}" if node.left.char is not None else node.left.freq
            G.add_node(node_count, label=label)
            G.add_edge(parent, node_count)
            stack.append((node.left, depth + 1, node_count))
            pos[node_count] = (depth * 2, -node_count)
        if node.right:
            node_count += 1
            label = f"{node.right.char}\n{node.right.freq}" if node.right.char is not None else node.right.freq
            G.add_node(node_count, label=label)
            G.add_edge(parent, node_count)
            stack.append((node.right, depth + 1, node_count))
            pos[node_count] = (depth * 2 + 1, -node_count)

    labels = nx.get_node_attributes(G, 'label')
    nx.draw(G, pos=pos, labels=labels, with_labels=True, node_size=2000, node_color='skyblue', font_size=10)
    plt.show()

def main():
    text = input("Enter the text to encode: ")
    root = build_huffman_tree(text)
    codes = generate_huffman_codes(root)
    print("Huffman Codes:")
    for char, code in sorted(codes.items()):
        print(f"{char}: {code}")
    visualize_huffman_tree(root)

if __name__ == "__main__":
    main()
