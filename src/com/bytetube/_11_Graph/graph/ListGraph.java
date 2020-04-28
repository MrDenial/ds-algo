package com.bytetube._11_Graph.graph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.bytetube._11_Graph.MinHeap;
import com.bytetube._11_Graph.UnionFind;

@SuppressWarnings("unchecked")
public class ListGraph<V, E> extends Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    public ListGraph() {}
    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();
        Vertex(V value) {

            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            return Objects.equals(value, ((Vertex<V, E>)obj).value);
        }
        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }
        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
            //this.weight = null;
        }

        EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>) obj;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }
        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }
    }


    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {//lambda
        return weightManager.compare(e1.weight, e2.weight);
    };

    public void print() {
        System.out.println("[vertex]-------------------");
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
            System.out.println("out-----------");
            System.out.println(vertex.outEdges);
            System.out.println("in-----------");
            System.out.println(vertex.inEdges);
        });

        System.out.println("[edge]-------------------");
        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from,fromVertex);
        }


        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to,toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);

    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v,new Vertex<>(v));


    }

    /**
     * 对容器的操作
     * Read：foreach
     * write：线程不安全
     * Iterator lock obj
     * @param v
     */

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null)  return;

//        for (Edge edge:vertex.outEdges) {
//            Vertex to = edge.to;
//            edges.remove(edge);
//            to.inEdges...
//        }

        for (Iterator<Edge<V,E>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);//d.inEdges
            iterator.remove();
            edges.remove(edge);
        }
        for (Iterator<Edge<V,E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);//c.outEdges
            iterator.remove();
            edges.remove(edge);
        }

    }

    public void bfs(V begin){

        Set<Vertex<V,E>> visited = new HashSet<>();
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Queue<Vertex<V,E>> queue = new LinkedList<>();//
        queue.add(beginVertex);
        visited.add(beginVertex);
        while (!queue.isEmpty()){
            Vertex<V, E> polled = queue.poll();
            System.out.println(polled.value);

            for (Edge<V,E> outEdge :polled.outEdges) {
                if (visited.contains(outEdge.to)) continue;
                queue.add(outEdge.to);
                visited.add(outEdge.to);
            }
        }

    }



    @Override
    //类似二叉树层序遍历
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;

        Set<Vertex<V,E>> visited = new HashSet<>();
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Queue<Vertex<V,E>> queue = new LinkedList<>();//
        queue.add(beginVertex);
        visited.add(beginVertex);
        while (!queue.isEmpty()){
            Vertex<V, E> polled = queue.poll();
            //System.out.println(polled.value);
            if (visitor.visit(polled.value) ) return;

            for (Edge<V,E> outEdge :polled.outEdges) {
                if (visited.contains(outEdge.to)) continue;
                queue.add(outEdge.to);
                visited.add(outEdge.to);
            }
        }

    }


    public void dfsRecursion(V begin){
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        dfs(beginVertex,new HashSet<>());//尾递归 tail recursion

    }

    private void dfs(Vertex<V,E> vertex,Set<Vertex<V, E>> visited) {//0
        System.out.println(vertex.value);
        visited.add(vertex);
        for (Edge<V,E> edge:vertex.outEdges) {
            if (visited.contains(edge.to)) {
                continue;
            }
            dfs(edge.to,visited);//tail recursion
        }

    }

    /**
     *1.先打印当前顶点（第一个顶点肯定是没有被访问过的，之后的顶点在打印之前，需要先判断是否已经被访问过），然后从outedges选择一条边
     *2.将被选择的from，to顺序压栈
     *3.打印被选择的边上的to
     *4.将to加入到已经访问的容器中
     *5.break（不去访问outedges中的其他边，而是访问一条边上的剩余结点）
     *6.弹出栈顶元素
     */
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V,E>> visited = new HashSet<>();
        Stack<Vertex<V,E>> stack = new Stack<>();

        stack.push(beginVertex);
        visited.add(beginVertex);
        if (visitor.visit(begin)) return;
        while (!stack.isEmpty()){
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V,E> edge:vertex.outEdges) {
                if (visited.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                visited.add(edge.to);
                if (visitor.visit(edge.to.value)) return;
                break;
            }
        }

    }


    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V,E>> queue = new LinkedList<>();
        Map<Vertex<V,E>,Integer> ins = new HashMap<>();

        vertices.forEach((V v,Vertex<V,E> vertex)->{
            int in = vertex.inEdges.size();
            if (in == 0) {
                queue.offer(vertex);
            }else {
                ins.put(vertex,in);
            }
        });
        while (!queue.isEmpty()){
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);

            for (Edge<V,E> edge:vertex.outEdges) {
                int toIn = ins.get(edge.to)-1;
                if (toIn == 0) {
                    queue.offer(edge.to);
                }else {
                    ins.put(edge.to,toIn);
                }
            }

        }
        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return kruskal();
        //return Math.random() > 0.5 ? prim() : kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Set<Vertex<V,E>> addedVertices = new HashSet<>();//S

            Set<EdgeInfo<V,E>> edgeInfos = new HashSet<>();//A 边集
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        Vertex<V, E> vertex = iterator.next();//A 顶点
        addedVertices.add(vertex);//A--->S
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);
        while (!heap.isEmpty()&& addedVertices.size()<vertices.size()){
            Edge<V, E> removed = heap.remove();//AB
            if (addedVertices.contains(removed.to) ) {
                continue;
            }
            edgeInfos.add(removed.info());//AB--->A边集
            addedVertices.add(removed.to);//B--->S
            heap.addAll(removed.to.outEdges);
        }
        return  edgeInfos;

    }

    private Set<EdgeInfo<V, E>> kruskal() {
        Set<EdgeInfo<V,E>> edgeInfos = new HashSet<>();//A 边集
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
        UnionFind<Object> unionFind = new UnionFind<>();
        vertices.forEach((V v,Vertex<V,E> vertex)->{
            unionFind.makeSet(vertex);//初始化：使得每个顶点成为一个单独的并查集
        }
        );
        while (!heap.isEmpty() && edgeInfos.size()<vertices.size()-1){
            Edge<V, E> removed = heap.remove();
            //如果一条边上的2个顶点都在集合中，则说明成环，当前这边就不能加入到mst当中
            if (unionFind.isSame(removed.from,removed.to) ) {
                continue;
            }
            edgeInfos.add(removed.info());
            unionFind.union(removed.from,removed.to);

        }
        return edgeInfos;
    }

    //从给定的一个顶点的outedges中选取最小的路径信息
    private Entry<Vertex<V, E>, E> getMinPath1(Map<Vertex<V, E>, E> paths) {
        return null;
    }

    //只包含起点到其他点的路径长度
    @Override
    public Map<V, E> shortestPath1(V begin) {//A
        return null;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    @SuppressWarnings("unused")
    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        return null;
    }


    /**
     * 松弛
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
     */
    private boolean relax(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {


        return true;
    }

    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        return null;
    }

    /**
     * 松弛
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
     */
    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {

    }





    /**
     * 从paths中挑一个最小的路径出来
     * @param paths
     * @return
     */
    private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        return null;
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {//floyd
        return null;
    }

    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
        return null;
    }



}
