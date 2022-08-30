package examen;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

public class Principal {

	public static void main(String[] args) throws SQLException {
		
		DAOAlumno.eliminarTodo();
		DAOProfesor.eliminarTodo();
		
		
		
		
		Profesor p1 = new Profesor();
		p1.setNombre("James Wilkins");
		p1.setTelefono("099255200");
		 
		
		if(DAOProfesor.insertarProfesor(p1)) { 
			System.out.println("Profesor ingreso OK -> "+p1.getNombre());
		}
		
		Profesor p2 = new Profesor();
		p2.setNombre("Laura Gates");
		p2.setTelefono("094520520");
		
		if(DAOProfesor.insertarProfesor(p2)) { 
			System.out.println("Profesor ingreso OK -> "+p2.getNombre());
		}
		
		Profesor p3 = new Profesor();
		p3.setNombre("Marta Logar");	
		p3.setTelefono("43626200");
		
		if(DAOProfesor.insertarProfesor(p3)) { 
			System.out.println("Profesor ingreso ok -> "+p3.getNombre());
		}
		
		//Muestra por consola el listado de usuarios
		ResultSet res = DAOProfesor.listadoTotal();
		listarProfesores(res);
				
		// Modificar Profesor
		// Esta funcion me busca el nombre del profesor
		// y me carga en la variable p1 el valor de la
		// id correspondiente.
		
		
		
		
		
		if(DAOProfesor.modificarProfesor(p1)) { 
			System.out.println("<<<Profesor modificado con éxito>>>");
		}
		
		
		
		// Damos de baja un Profesor
		p3=DAOProfesor.buscarNombre(p3.getNombre());
		if (DAOProfesor.eliminarProfesor(p3)) {
			System.out.println("Eliminamos con exito :"+p3.getNombre());
		}
		
		//Listamos nuevamente
		res = DAOProfesor.listadoTotal();
		listarProfesores(res);
		// Le vamos a asignar los id a p1 y p2 para poder
		// dar de alta los Alumnos.
		p1=DAOProfesor.buscarNombre(p1.getNombre());
		p2=DAOProfesor.buscarNombre(p2.getNombre());
		System.out.println(p1);
		System.out.println(p2);
		
		// Creamos Alumnos y las damos de alta
		LinkedList<Alumno> listaAlu = new LinkedList<Alumno>();
		listaAlu=crearAlumnos(p1,p2);
		
		for(Alumno a: listaAlu ) {
			if(DAOAlumno.insertarAlumno(a)) {
				System.out.println("Alta ok :"+a.getNombre());
			}
		}
		
		
		
		
		LinkedList<Alumno> resul_list=DAOAlumno.listadoAlumno();
		listarAlumnos(resul_list);
		
		
		// Modificar un dato de dos Alumnos
		Alumno a2;
		a2=listaAlu.get(1);
		a2=DAOAlumno.buscarNombre(a2.getNombre());
		a2.setFechaing(LocalDate.of(2021,04,24));
		a2.setLenguaje(Tipo.PYTHON);
		if(DAOAlumno.modificarAlumno(a2)) {
			System.out.println("Modificado OK "+a2.getNombre());
		}
		
		
		a2=listaAlu.get(5);
		a2=DAOAlumno.buscarNombre(a2.getNombre());
		a2.setLenguaje(Tipo.GO);
		a2.setNombre("Martin Fierro");
		if(DAOAlumno.modificarAlumno(a2)) {
			System.out.println("Modificado OK "+a2.getNombre());
		}
		
		resul_list=DAOAlumno.listadoAlumno();
		listarAlumnos(resul_list);
		System.out.println();
		System.out.println("<<<<<< Consulta Final >>>>>>>>");
		System.out.println();
		System.out.print("La cantidad de alumnos que estudian JAVA son :");
		System.out.println(DAOAlumno.cantidad("JAVA"));
		
		System.out.print("La cantida que estudian PYTHON :");
		System.out.println(DAOAlumno.cantidad("PYTHON"));
		
		System.out.print("La cantidad que estudian GO  :");
		System.out.println(DAOAlumno.cantidad("GO"));
		
		
		
		
		
	}
	// Metodo o funcion para listar los profesores
	public static void listarProfesores(ResultSet r) throws SQLException {
		System.out.println("--- Listado de Profesores ---");
		while(r.next()) {
			System.out.print("Nombre: " + r.getString("NOMBRE")+" -- ");
			System.out.print("Telefono: "+r.getString("TELEFONO")+" -- ");
			System.out.println("Id : " + r.getInt("ID_PROFESOR"));
		}
		System.out.println("=================================");
	}
	
	// Metodo o funcion para listar los Alumnos
	public static void listarAlumnos(LinkedList<Alumno> lista) {
		System.out.println("*** Listado de ALUMNOS ***");
		for(Alumno a: lista) {
			System.out.print(a.getId_alumno()+" -- ");
			System.out.print(a.getNombre()+" -- ");
			System.out.print(a.getFechaing()+" -- ");
			System.out.print(a.getLenguaje()+" -- ");
			System.out.println(a.getProfesor().getNombre()+" >> ");
			
		}
		
		// otro metodo para imprimir podria ser :
		// for(Persona p: lista) {
		//    System.out.println(p);// utilizando el metodo toString de
		                            // la clase 
		// }
		
		
	}


	public static LinkedList<Alumno> crearAlumnos(Profesor p1,Profesor p2) {
		LinkedList<Alumno> lista = new LinkedList<Alumno>();
		Alumno a1=new Alumno("Oscar Correa",LocalDate.of(2018,5,02),Tipo.JAVA,p1);
		Alumno a2=new Alumno("Ana Duarte",LocalDate.of(2015,6,23),Tipo.PYTHON,p1);
		Alumno a3=new Alumno("Arturo Vidal",LocalDate.of(2019,10,12),Tipo.JAVA,p1);
		Alumno a4=new Alumno("Claudio Gallo",LocalDate.of(2021,4,30),Tipo.JAVA,p1);
		Alumno a5=new Alumno("Hugo Rivas",LocalDate.of(2020,9,25),Tipo.GO,p2);
		Alumno a6=new Alumno("Laura Canosa",LocalDate.of(2021,1,5),Tipo.PYTHON,p2);
		Alumno a7=new Alumno("Karina Rojas",LocalDate.of(2021,6,26),Tipo.GO,p2);
		Alumno a8=new Alumno("Marta Fumero",LocalDate.of(2020,8,20),Tipo.GO,p1);
		Alumno a9=new Alumno("Elbio Bruno",LocalDate.of(2021,5,21),Tipo.PYTHON,p2);
		Alumno a10=new Alumno("Carlos Grauer",LocalDate.of(2020,10,13),Tipo.PYTHON,p1);
		Alumno a11=new Alumno("Gustavo Jodal",LocalDate.of(2021,12,28),Tipo.JAVA,p1);
		Alumno a12=new Alumno("Brian Corbo",LocalDate.of(2022,4,10),Tipo.GO,p1);
		Alumno a13=new Alumno("Enzo Matei",LocalDate.of(2022,3,25),Tipo.JAVA,p1);
		
		Alumno a14=new Alumno("William Lauson",LocalDate.of(2017,9,12),Tipo.JAVA,p1);
		lista.add(a1);
		lista.add(a2);
		lista.add(a3);
		lista.add(a4);
		lista.add(a5);
		lista.add(a6);
		lista.add(a7);
		lista.add(a8);
		lista.add(a9);
		lista.add(a10);
		lista.add(a11);
		lista.add(a12);
		lista.add(a13);
		lista.add(a14);
		
		return lista;
	}

}
