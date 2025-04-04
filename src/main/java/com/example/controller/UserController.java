package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Invoice;
import com.example.entity.InvoiceTemplate;
import com.example.entity.User;
import com.example.repository.InvoiceRepository;
import com.example.repository.InvoiceTemplateRepository;
import com.example.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InvoiceTemplateRepository invoiceTemplateRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@GetMapping("/getUser")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@PostMapping("/createInvoiceTemplate")
	public ResponseEntity<InvoiceTemplate> createUser(@RequestBody InvoiceTemplate invoiceTemplate) {
		InvoiceTemplate savedInvoiceTemplate = invoiceTemplateRepository.saveAndFlush(invoiceTemplate);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoiceTemplate);
	}
	
	@GetMapping("/getInvoiceTemplate")
	public List<InvoiceTemplate> getInvoiceTemplate() {
		return invoiceTemplateRepository.findAll();
	}
	
	/*
	 * @GetMapping("/getInvoiceTemplate/{id}") public
	 * ResponseEntity<InvoiceTemplate> getInvoiceTemplateById(@PathVariable Long id)
	 * { return invoiceTemplateRepository.findById(id) .map(ResponseEntity::ok)
	 * .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); }
	 */
	
	@GetMapping("/getInvoiceTemplateByIdOrName")
	public ResponseEntity<InvoiceTemplate> getInvoiceTemplate(
	        @RequestParam(required = false) Long id, 
	        @RequestParam(required = false) String name) {

	    if (id != null) {
	        return invoiceTemplateRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	    } else if (name != null) {
	        return invoiceTemplateRepository.findByName(name)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	    }

	    return ResponseEntity.badRequest().body(null);
	}

	
	@PostMapping("/createInvoice")
	public ResponseEntity<Invoice> createUser(@RequestBody Invoice invoice) {
		Invoice savedInvoiceTemplate = invoiceRepository.saveAndFlush(invoice);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoiceTemplate);
	}
	
	@GetMapping("/getInvoices")
	public List<Invoice> getInvoices() {
		return invoiceRepository.findAll();
	}
	
	/*
	 * @GetMapping("/getInvoices/{id}") public ResponseEntity<Invoice>
	 * getInvoicesById(@PathVariable Long id) { return
	 * invoiceRepository.findById(id) .map(ResponseEntity::ok)
	 * .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); }
	 */
	
	@GetMapping("/getInvoiceByIdOrName")
	public ResponseEntity<Invoice> getInvoice(
	        @RequestParam(required = false) Long id, 
	        @RequestParam(required = false) String name) {

	    if (id != null) {
	        return invoiceRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	    } else if (name != null) {
	        return invoiceRepository.findByName(name)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	    }

	    return ResponseEntity.badRequest().body(null);
	}

}
