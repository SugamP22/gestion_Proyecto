package gestionProyectos;

public class Proyecto {
    private String nombre;
    private EstadoProyecto estado;
    private Prioridad prioridad;

    public Proyecto(String nombre, Prioridad prioridad) {
        this.nombre = nombre;
        this.estado = EstadoProyecto.NO_INICIADO; // Default state
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void avanzarEstado() {
        if (estado == EstadoProyecto.NO_INICIADO) {
            estado = EstadoProyecto.ENPROGRESO;
        } else if (estado == EstadoProyecto.ENPROGRESO) {
            estado = EstadoProyecto.COMPLETADO;
        } else {
            System.out.println("El proyecto ya está COMPLETADO.");
        }
    }

    @Override
    public String toString() {
        return "Proyecto [Nombre=" + nombre + ", Estado=" + estado + ", Prioridad=" + prioridad + "]";
    }
}
