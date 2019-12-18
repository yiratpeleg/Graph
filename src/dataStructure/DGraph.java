package dataStructure;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class DGraph implements graph{

	private HashMap<Integer, node_data> nodes = new HashMap<>();                   //integer for key, node for value
	private HashMap<Integer, HashMap<Integer,edge_data>> edges = new HashMap<>();  //integer for src, hashmap for dest
	private static int nodesCount = 0;
	private static int edgesCount = 0;
	private static int modeCount = 0;

	@Override
	public node_data getNode(int key) {
		return this.nodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return this.edges.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.nodes.put(n.getKey(),n);
		nodesCount++;
		modeCount++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		Edge temp = new Edge(src,dest,0,w,"");
		if(this.edges.get(src)!=null){
			if(this.edges.get(src).get(dest)==null) {
				this.edges.get(src).put(dest, temp);
			}
			else{
				removeEdge(src, dest);
				this.edges.get(src).put(dest,temp);
			}
		}
		else{
			HashMap<Integer,edge_data> add = new HashMap<>();
			this.edges.put(src,add).put(dest,temp);
		}
		edgesCount++;
		modeCount++;
	}

	@Override
	public Collection<node_data> getV() {
		return this.nodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return this.edges.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		node_data nd = this.nodes.get(key);
		if(nd!=null) {
			this.nodes.remove(key);
			if (this.edges.get(key) != null) {
				this.edges.remove(key);
			}
			Iterator it = this.edges.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry mp = (Map.Entry) it.next();           //give the key
				int temp = ((int) mp.getKey());                 //save the key
				if (this.edges.get(temp).get(key) != null) {
					removeEdge(temp, key);
				}
			}
			nodesCount--;
			modeCount++;
		}
		return nd;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data ed = this.edges.get(src).get(dest);
		if(ed!=null) {
			this.edges.get(src).remove(dest);
			edgesCount--;
			modeCount++;
		}
		return ed;
	}

	@Override
	public int nodeSize() {
		return nodesCount;
	}

	@Override
	public int edgeSize() {
		return edgesCount;
	}

	@Override
	public int getMC() {
		return modeCount;
	}

}
