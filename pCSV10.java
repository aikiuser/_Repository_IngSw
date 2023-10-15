import java.util.ArrayList;
import java.util.List;

/*--------------------
 * Procedura di Gestione dei Teams di Ingegneria del Software
 * L'applicazione utilizza diversi metodi memorizzati nella
 * classe Services:
 * 		public String[][] LeggiCSV(String filePath, String delimiter)
 *		public int calcolaPuntiMatrice(String[][] sMatPartecipanti, String sGruppo)	
 *		public double calcolaMediaGruppo(int nSomma, String sGruppo) 
 *		public double calcolaMediaDueGruppi(double dMedia1, double dMedia2)
 *	
 *		public int getNumeroMatrice(String[][] cMatPartecipanti, String sRiga, String sColonna) 
 *		public String creaStringaGeneraGruppi(String [][] sMatPartecipanti)
 *		public boolean isOrdered(String str)
 *		public List<String> findPermutations(String str)
 *		public boolean isNumeric(String s)
  *--------------------*/

public class pCSV10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i=0;
		int nTotale=0;
		String sGruppo1="ABC";
		String sGruppo2="ABC";
		String sGruppo1Max = "";
		String sGruppo2Max = "";
		String sTest = "";
		String sGenerata="";
		String sNomeFile="";
		double dMedia1 = 0.0;
		double dMedia2 = 0.0;
		double dMediaTot = 0.0;
		double dMediaTotMax = 0.0;
		int nLimit1=0;
		int nLimit2=0;
		
		System.out.println("Programma di Gestione dei Teams in Ingegneria del Software");
		System.out.println("----------------------------------------------------------");
		System.out.println(" ");
	
// Creo un'istanza dei Servizi		
		Services Serv = new Services();
				
// Leggi il File TXT contenente il File di Configurazopme		
		String filePath = "C:/Users/CORLANDO/eclipse-workspace/TestB11_CSV_Example/src/Conf.txt";
		String delimiter = "=";
		String sConf [][] = Serv.LeggiTXT(filePath, delimiter);
				
// Leggi il File CSV contenente i Punteggi dei Partecipanti (dati dal File di Configurazione)
		filePath  = sConf[0][1];
		sNomeFile = sConf[1][1];
		filePath = filePath + sNomeFile;
		delimiter = sConf[2][1];
		String sMatPartecipanti [][] = Serv.LeggiCSV(filePath, delimiter);
		
// crea la permutazione di tutti i possibili Gruppi di Partecipanti attraverso il
//  metodo findPermutations.
		sTest = Serv.creaStringaGeneraGruppi(sMatPartecipanti);
		List<String> partial = Serv.findPermutations(sTest);
        
//		System.out.println("main: "+partial);
		
// Vengono scorse  tutte le possibili combinazioni dei Partecipanti memorizzati nell'array
//   denominato 'partial'. Ogni occorrenza è una stringa che contiene i due Gruppi
//   da valutare. Ad esempio ABCDEF viene suddiviso in ABC e in DEF. I due Gruppi
//   vengono a loro volta valutati separatamente.		
		i=0;
		while (i < partial.size()) {
			sGenerata = partial.get(i);
			nLimit1 = sGenerata.length() / 2;
			nLimit2 = sGenerata.length();

// prendo il Gruppo 1 e calcolo la prima Media			
	        sGruppo1=sGenerata.substring(0, nLimit1);
			nTotale = Serv.calcolaPuntiMatrice(sMatPartecipanti, sGruppo1);
			dMedia1 = Serv.calcolaMediaGruppo(nTotale, sGruppo1);
//			System.out.println(sGruppo1 + " " +nTotale + " "+ dMedia1);

// prendo il Gruppo 2 e calcolo la seconda Media			
			sGruppo2=sGenerata.substring(nLimit1, nLimit2);
			nTotale = Serv.calcolaPuntiMatrice(sMatPartecipanti, sGruppo2);
			dMedia2 = Serv.calcolaMediaGruppo(nTotale, sGruppo2);
//			System.out.println(sGruppo2 + " " +nTotale + " "+ dMedia2);

// calcolo la Media dei due Gruppi			
			dMediaTot=Serv.calcolaMediaDueGruppi(dMedia1, dMedia2);
//			System.out.println("Media Totale: "+dMediaTot);

// Verifico se ho trovato i due Gruppi Migliori valutando il valore della MediaTotale			
			if ( dMediaTot > dMediaTotMax ) {
				sGruppo1Max = sGruppo1;
				sGruppo2Max = sGruppo2;
				dMediaTotMax = dMediaTot;
			}
			i++;
		}
		
		System.out.println("Il Miglior risultato, Gruppo1: " + sGruppo1Max + "   - Gruppo2: " + sGruppo2Max + "   - Media ottenuta: " + dMediaTotMax);
				
	}

}
