/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

/**
 *
 * @author ASUS
 */
public class Vertex <T>{
    T vertexinfo;

    public Vertex() {
        this(null);
    }
    
    public Vertex(T vertexinfo) {
        this.vertexinfo = vertexinfo;
    }

    public T getVertexinfo() {
        return vertexinfo;
    }
    
}
