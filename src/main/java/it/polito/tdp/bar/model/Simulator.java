package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	// coda
	private PriorityQueue<Event> queue = new PriorityQueue<>();

	// parametri simulazione
	private LocalTime oraApertura = LocalTime.of(8, 00);
	private LocalTime oraChiusura = LocalTime.of(23, 00);

	private Duration T_IN = Duration.of(10, ChronoUnit.MINUTES);

	// modello del mondo
	List<Tavolo> tavoli = new ArrayList<>();

	public List<Tavolo> getTavoli() {
		return tavoli;
	}

	// setto modello del mondo all'inizio
	public void setTavoli() {
		tavoli.clear();
		tavoli.add(new Tavolo(10, null));
		tavoli.add(new Tavolo(10, null));

		tavoli.add(new Tavolo(8, null));
		tavoli.add(new Tavolo(8, null));
		tavoli.add(new Tavolo(8, null));
		tavoli.add(new Tavolo(8, null));

		tavoli.add(new Tavolo(6, null));
		tavoli.add(new Tavolo(6, null));
		tavoli.add(new Tavolo(6, null));
		tavoli.add(new Tavolo(6, null));

		tavoli.add(new Tavolo(4, null));
		tavoli.add(new Tavolo(4, null));
		tavoli.add(new Tavolo(4, null));
		tavoli.add(new Tavolo(4, null));
		tavoli.add(new Tavolo(4, null));

	}

	// Valori da calcolare
	private Integer clientiSoddisfatti;

	private Integer clientiTotali;

	public Integer getClientiSoddisfatti() {
		return clientiSoddisfatti;
	}

	public Integer getClientiInsoddisfatti() {
		return clientiTotali - clientiSoddisfatti;
	}

	public Integer getClientiTotali() {
		return clientiTotali;
	}

	public void run() {
		clientiSoddisfatti = 0;

		clientiTotali = 0;
		this.queue.clear();
		LocalTime oraArrivoCliente = this.oraApertura;

		for (int i = 1; i <= 2000 && !oraArrivoCliente.isAfter(oraChiusura); i++) {
			Event e = new Event(oraArrivoCliente, EventType.ARRIVO_CLIENTI);
			this.queue.add(e);
			Random r = new Random();
			Integer casuale = r.nextInt((10 - 1)) + 1;
			Duration interArrivo = Duration.of(casuale, ChronoUnit.MINUTES);
			this.T_IN = interArrivo;
			oraArrivoCliente = oraArrivoCliente.plus(T_IN);

		}

		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}

	private Tavolo occupaTavoloLibero(int numeroClienti) {
		for (Tavolo t : tavoli) {
			if (t.getOccupato() == null && numeroClienti <= t.getPosti() && t.getPosti() <= 2 * numeroClienti) {
				return t;
			}

		}
		return null;
	}

	private void processEvent(Event e) {
		Random r = new Random();
		int numeroClienti = r.nextInt((10 - 1) + 1) + 1;
		switch (e.getType()) {
		case ARRIVO_CLIENTI:
			Tavolo t = this.occupaTavoloLibero(numeroClienti);
			if (t != null) {
				this.clientiTotali += numeroClienti;
				this.clientiSoddisfatti += numeroClienti;

				Random ran = new Random();
				int permanenza = ran.nextInt((120 - 60) + 1) + 60;
				Duration service = Duration.of(permanenza, ChronoUnit.MINUTES);
				Event nuovo = new Event(e.getTime().plus(service), EventType.PARTENZA_CLIENTI);
				this.queue.add(nuovo);
				t.setOccupato(nuovo);

			}

			else {
				double tolleranza = Math.random();
				if (tolleranza < 0.9) {

					this.clientiTotali += numeroClienti;

				} else if (tolleranza >= 0.9) {

					this.clientiTotali += numeroClienti;
					this.clientiSoddisfatti += numeroClienti;

					// (Intervallo di utilizzo 1,2 ore come detto nel testo dell'esercizio)
					Random random = new Random();
					Integer durata = random.nextInt((120 - 60) + 1) + 60;
					Duration service = Duration.of(durata, ChronoUnit.MINUTES);

					Event nuovo = new Event(e.getTime().plus(service), EventType.PARTENZA_CLIENTI);
					this.queue.add(nuovo);

				}
			}
			break;

		case PARTENZA_CLIENTI:
			for (Tavolo temp : tavoli) {
				if(temp.getOccupato()!=null) {
				if (temp.getOccupato().equals(e)) {
					temp.setOccupato(null);
					break;
				}
				}
			}

			break;

		}

	}

}
