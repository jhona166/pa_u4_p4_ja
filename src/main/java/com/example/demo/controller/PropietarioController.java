package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Propietario;
import com.example.demo.service.IPropietarioService;

@Controller
//Buena practica plural
@RequestMapping("/propietarios")
public class PropietarioController {
	@Autowired
	private IPropietarioService iPropietarioService;
	//http://localhost:8080/concesionario/propietarios/buscar
	@GetMapping("/buscarTodos")
	public String buscarTodos(Model modelo ) {
		List<Propietario> lista = this.iPropietarioService.buscarTodos();
		modelo.addAttribute("propietarios",lista);
		return "vistaListaPropietarios";
	}
	@GetMapping("/buscarPorID/{idPropietario}")
	//http://localhost:8080/concesionario/propietarios/buscarPorID/1
	public String buscarPorId(@PathVariable("idPropietario") Integer id,Model model) {
		Propietario prop = this.iPropietarioService.buscarPorId(id);
		model.addAttribute("propietario",prop);
		
		return "vistaPropietario";
	}
	@PutMapping("/actualizar/{idPropietario}")
	public String actualizarPropietario(@PathVariable("idPropietario")Integer id, Propietario propietario) {
		this.iPropietarioService.actualizar(propietario);
		return "redirect:/propietarios/buscar";
		
	}
}
