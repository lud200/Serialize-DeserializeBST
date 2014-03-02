package Serialize;
import java.io.*;
import java.util.*;

public class serialize {
	public static void main(String args[]) throws IOException{
		BinaryTree b2=new BinaryTree();
		b2.insert(26);
		b2.insert(10);
		b2.insert(4);
		b2.insert(11);
		b2.insert(3);
		b2.insert(40);
		b2.insert(33);
		//System.out.println(b2.preorder());
		String path="C:\\1.txt";
		Serialize(b2, path);
		BinaryTree t1=Deserialize(path);
		System.out.println(t1.preorder());
	}
	public static void Serialize(BinaryTree tree, String s) throws IOException{
		String res=tree.preorder();
		FileWriter fstream=new FileWriter(s);
		BufferedWriter out=new BufferedWriter(fstream);
		out.write(res);
		out.close();
	}
	public static BinaryTree Deserialize(String s) throws NumberFormatException, IOException, FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(s));
        BinaryTree tree = new BinaryTree();
        String line = null;
        int n;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            for (String part : parts) {
            	n=Integer.parseInt(part);
                tree.insert(n);
            }
        }
        reader.close();
        return tree;
	}
}
class BinaryTree{
	private class Node{
		Node left;
		Node right;
		int data;
		Node(int newdata){
			left=null;
			right=null;
			data=newdata;
		}
	}
	private Node root;
	public void BinaryTree(){
		root=null;
	}
	/*Serializing a tree is nothing but 
	 * writing a tree to a file and reading the same tree back
	 * on some other computer.
	 */
	public String preorder(){
		StringBuilder sb=new StringBuilder();
		preorder(root, sb);
		return sb.toString();
	}
	public void preorder(Node node, StringBuilder sb){
		if(node==null){
			sb.append("");
			return;
		}
		sb.append(node.data+" ");
		preorder(node.left, sb);
		preorder(node.right, sb);
	}
	//Insert
	public void insert(int data){
		Node temp=root;
		Node newnode=new Node(data);
		newnode.left=null;
		newnode.right=null;
		newnode.data=data;
		root=insert(root, newnode);
	}
	public Node insert(Node temp, Node newnode){
		int data;
		if(temp==null){
			temp=newnode;
		}
		else{
			if(temp.data<=newnode.data){
				insert(temp.right, newnode);
				if(temp.right==null)
					temp.right=newnode;
			}
			else{
				insert(temp.left, newnode);
				if(temp.left==null)
					temp.left=newnode;
			}
		}
		return temp;
	}
}