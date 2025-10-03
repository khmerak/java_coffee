package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.setec.entities.Booked;
import com.setec.repos.BookedRepo;
import com.setec.telegrambot.MyTelegramBot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MyController {
	@GetMapping({ "/", "/home" })
	public String home(Model mod) {
		Booked booked = new Booked(2, "Khemrak", "097978376885", "kh@gmail.com", "09/04/2025", "7:45 AM", 2);
		mod.addAttribute("booked", booked);
		return "index";
	}

	@GetMapping({ "/about" })
	public String about() {
		return "about";
	}

	@GetMapping("/service")
	public String service() {
		return "service";
	}

	@GetMapping({ "/menu" })
	public String menu() {
		return "menu";
	}

	@GetMapping("/reservation")
	public String reservation(Model mod) {
		Booked booked = new Booked(1, "Tri Bun Khemrak", "09787656787", "khem@gmail.com", "09/03/2025", "7:45 AM", 6);
		mod.addAttribute("booked", booked);
		return "reservation";
	}

	@GetMapping({ "/testimonial" })
	public String testimonial() {
		return "testimonial";
	}

	@GetMapping({ "/contact" })
	public String contact() {
		return "contact";
	}

	@Autowired
	private BookedRepo bookedRepo;

	@Autowired
	private MyTelegramBot bot;

	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked) {
		bookedRepo.save(booked);

		String msg = """
				<b>☕️New Booking Coffee☕️</b>\n
				<b>-------------------------------------</b>\n
				🆔  <b>ID:</b> %d\n
				👤  <b>Name:</b> %s\n
				📧  <b>Email:</b> %s\n
				📞  <b>Phone:</b> %s\n
				📅  <b>Date:</b> %s\n
				⏰  <b>Time:</b> %s\n
				👨  <b>Persons:</b> %d\n
				<b>-------------------------------------</b>
				""".formatted(booked.getId(), booked.getName(), booked.getEmail(), booked.getPhoneNumber(),
				booked.getDate(), booked.getTime(), booked.getPerson());

		// send to Telegram with parseMode=HTML
		bot.message(msg);

		return "success";
	}

}
