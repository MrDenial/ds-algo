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


    public void dfsRecursion(V begin){}

    private void dfs(Vertex<V,E> vertex,Set<Vertex<V, E>> visited) {//0
    }

    /**
     * 1.先打印根节点（判断是否已经被访问过），然后从outEdges中选择一条边
     * 2.将被选择边的from、to按顺序入栈
     * 3.打印被选择边的to
     * 4.将to加到已经访问的范围中
     * 5.break（不去访问outEdges中的其他边，而且访问一条边上的剩余点）
     * 6.弹出栈顶元素
     * @param begin
     * @param visitor
     */
    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) { }

    public void dfs2(V begin) { }

    private void dfs2(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {}

    @Override
    public List<V> topologicalSort() {

        return null;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return kruskal();
        //return Math.random() > 0.5 ? prim() : kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {

        return null;
    }

    private Set<EdgeInfo<V, E>> kruskal() {
        return null;
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
