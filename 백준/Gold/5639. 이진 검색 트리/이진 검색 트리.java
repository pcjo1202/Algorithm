import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> preOrder;
    static Node root;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        preOrder = new ArrayList<>();
        String str;

        while ((str = br.readLine()) != null && !str.isEmpty()) {
            preOrder.add(Integer.parseInt(str));
        }

        // 트리 생성
        root = new Node(preOrder.get(0));
        for (int i = 1; i < preOrder.size(); i++) {
            insert(root, preOrder.get(i));
        }

        // 후위 순회 결과 출력
        postOrder(root);
        System.out.print(sb);
    }

    // 전위 순회를 트리에 노드를 삽입하는 함수
    static void insert(Node parent, int value) {
        // 삽입 하는 값이 parent 보다 작으면 왼쪽
        if (value < parent.data) {
            if (parent.left == null) {
                parent.left = new Node(value);
            } else {
                insert(parent.left, value);
            }
        } else {
            if (parent.right == null) {
                parent.right = new Node(value);
            } else {
                insert(parent.right, value);
            }
        }
    }

    // 후위 순회 (왼쪽-오른쪽-루트) 로 출력하는 함수
    static void postOrder(Node node) {
        // 기저 조건 없으면 종료
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.data).append('\n');
    }
}
