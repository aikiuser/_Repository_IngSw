import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * La Classe Services contiene tutti i servizi che dovranno gestire
 * l'applicazione di Gestione Teams
 */

public class Services {

/*--------------------
 * LeggiTXT permette di leggere un file txt contente la configurazione
 * @param filePath 		Nome del file
 * @param delimiter 	carattere di delimitazione dei campi TXT
 * @return String[][]   Matrice contenente i campi del file TXT	
 *--------------------*/

	public String[][] LeggiTXT(String filePath, String delimiter) 
	{ 

    String line = "";
    
    String [][] sConf = new String [20][20];
    
    int i=0;
    int j=0;
    
    try
    {
        FileReader fileReader = new FileReader(filePath);

        BufferedReader reader = new BufferedReader(fileReader);
        
        i=0;
        while ((line = reader.readLine()) != null)  
        {	
        	
        	String[] token = line.split(delimiter);    // separa ogni token delimitati da delimiter

        	for (j=0; j<token.length; j++) { 
        		sConf [i][j]=token[j];      		   //  copia tutti i token di una riga in una matrice 
        	}
        	i++;
        }
        
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    
    return sConf;
}
	/*--------------------
	 * LeggiCSV permette di leggere un file CSV contente i punteggi dei
	 *   Partecipanti ai Teams
	 * @param filePath 		Nome del file
	 * @param delimiter 	carattere di delimitazione dei campi CSV
	 * @return String[][]   Matrice contenente i campi del file CSV	
	 *--------------------*/

		public String[][] LeggiCSV(String filePath, String delimiter) 
		{ 

	    String line = "";
	    
	    String [][] sMatPartecipanti = new String [20][20];
	    
	    int i=0;
	    int j=0;
	    
	    try
	    {
	        FileReader fileReader = new FileReader(filePath);

	        BufferedReader reader = new BufferedReader(fileReader);
	        
	        i=0;
	        while ((line = reader.readLine()) != null)  
	        {	
	        	
	        	String[] token = line.split(delimiter);    // separa ogni token delimitati da delimiter

	        	for (j=0; j<token.length; j++) { 
	        		sMatPartecipanti [i][j]=token[j];      //  copia tutti i token di una riga in una matrice 
	        	}
	        	i++;
	        }
	        
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    
	    return sMatPartecipanti;
	}
	
/*--------------------
 * calcolaPuntiMatrice permette di calcolare il Punteggio di un Gruppo di Partecipanti 
 * @param sMatPartecipanti 		Matrice contenente i punteggi dei Partecipanti ai Teama
 * @param sGruppo 	            Gruppo di Partecipanti di cui calcolare il punteggio
 * @return int   				Punteggio calcolato	
 *--------------------*/

	public int calcolaPuntiMatrice(String[][] sMatPartecipanti, String sGruppo) 
	{ 

	    int dTotale=0;
	    int i=0;
	    int j=0;
	    String sRiga="";
	    String sColonna="";
	    int nSomma=0;
	    
// Dato un Gruppo calcola il Punteggio del Team utilizzando la Matrice sMatPartecipanti

	    for (i=0; i<sGruppo.length(); i++ ) {
			sRiga=String.valueOf(sGruppo.charAt(i));
			for (j=0; j<sGruppo.length(); j++ ) {
				sColonna=String.valueOf(sGruppo.charAt(j));
				nSomma = nSomma + getNumeroMatrice(sMatPartecipanti, sRiga, sColonna);
				
//				System.out.println(sRiga + " "+sColonna);
			}
		}
	
	    
	    return nSomma;
	}
/*--------------------
 * calcolaMediaGruppo permette di calcolare la Media delPunteggio ottenuto da un Team  
 * @param nSomma 	  Punteggio calcolato per un dato Team
 * @param sGruppo 	  Gruppo di Partecipanti (Team)
 * @return double     Media calcolata	
 *--------------------*/
	public double calcolaMediaGruppo(int nSomma, String sGruppo) 
	{ 

		double dMedia;
		
		dMedia = (nSomma * 1.0 / sGruppo.length());
		
	    return dMedia;
	}

/*--------------------
 * calcolaMediaDueGruppi permette di calcolare la Media , relativamente alla media di due Gruppi  
 * @param dMedia1 	  Media del Gruppo 1
 * @param dMedia2 	  Media del Gruppo 2
 * @return double     Media calcolata	
 *--------------------*/

	public double calcolaMediaDueGruppi(double dMedia1, double dMedia2) 
	{ 

		double dMedia;
		
		dMedia = (dMedia1 + dMedia2) / 2;
		
	    return dMedia;
	}

/*--------------------
 * getNumeroMatrice permette di estrarre un valore dalla Matrice dei Punteggi   
 * @param cMatPartecipanti 	Matrice contenente i punteggi dei Partecipanti ai Team
 * @param sRiga 	  		Riga della Matrice
 * @param scolonna 	  		Colonna della Matrice
 * @return int     			Punteggio estratto dalla Matrice dei Partecipanti	
 *--------------------*/
	
	public int getNumeroMatrice(String[][] cMatPartecipanti, String sRiga, String sColonna) 
	{ 

		int nNumero=0;
		int i=0;
		int j=0;

// Cerca Stringa 'offerente'
		while ( cMatPartecipanti[i][0].compareTo("offerente") != 0) {
//			System.out.println(cMatPartecipanti[i][0]);
			i++;					
			}			

//		System.out.println("Trovato offerente " + i );
		
// Cerca cColonna
		while ( cMatPartecipanti[i][j].compareTo(sColonna) != 0) {
//			System.out.println(cMatPartecipanti[i][j]);
			j++;					
		}			
		
// Cerca cRiga
		i++;
		while ( cMatPartecipanti[i][0].compareTo(sRiga) != 0) {
//			System.out.println(cMatPartecipanti[i][0]);
			i++;					
		}			
		
//		System.out.println("Cerco "+sRiga + " "+sColonna + " " + i + " " + j);
		
// estrai il valore dalla Matrice dei Punteggi
		if ( isNumeric(cMatPartecipanti[i][j]) ) {
			nNumero = Integer.parseInt( cMatPartecipanti[i][j].trim() );
		}
		
	    return nNumero;
	}
	
/*--------------------
 * getNumeroMatrice permette di estrarre un valore dalla Matrice dei Punteggi   
 * @param cMatPartecipanti 	Matrice contenente i punteggi dei Partecipanti ai Team
 * @param sRiga 	  		Riga della Matrice
 * @param scolonna 	  		Colonna della Matrice
 * @return int     			Punteggio estratto dalla Matrice	
 *--------------------*/

	public String creaStringaGeneraGruppi(String [][] sMatPartecipanti) 
	{ 

		int i=0;
		String sTemp="";

// Cerca Stringa 'offerente'
		while ( sMatPartecipanti[i][0].compareTo("offerente") != 0) {
//			System.out.println(cMatPartecipanti[i][0]);
			i++;					
			}			

//		System.out.println("Trovato offerente " + i );
		
// Cerca cRiga
		i++;
		while (sMatPartecipanti[i][0]!=null ) {
            sTemp = sTemp + sMatPartecipanti[i][0].trim() ;
			i++;					
		}					
		
	    return sTemp;
	}

/*--------------------
 * isOrdered permette di verifica se una Stringa(concatenazione di due Team)
 *   generata da un metodo che effettua una permutazione dei gruppi
 *   è coerente con la proceduta. Le due metà sono in ordine ascendente.    
 * @param str		Stringa di Input
 * @return boolean 	true se la Stringa è coerente con la procedura
 *--------------------*/

	public boolean isOrdered(String str) {
		char cAtt=' ';
		char cPrec=' ';
		int nLen = 0;
		int nLen1 = 0;
		int nLen2 = 0;
		int i=0;
		nLen = str.length();
		boolean bResult1 = true;
		boolean bResult2 = true;
		boolean bResult = false;
		nLen1 = nLen / 2;
		nLen2 = nLen - nLen1;
		
// verifica della stringa contenuta nella 1.a metà della stringa
		i=1;
		while (i<nLen1) {
			cAtt = str.charAt(i);
			cPrec = str.charAt(i-1);
			if (cAtt <= cPrec) {
				bResult1=false;
				break;
			}
			i++;
		}

// verifica della stringa contenuta nella 2.a metà della stringa
		i=nLen1+1;
		while (i<nLen) {
			cAtt = str.charAt(i);
			cPrec = str.charAt(i-1);
			if (cAtt <= cPrec) {
				bResult1=false;
				break;
			}
			i++;
		}

// se le due stringhe sono coerenti, allora la stringa di input è coerente

		if (bResult1 & bResult2) {
			bResult=true;
		}
		
		return bResult;
	}

/*--------------------
 * findPermutations  permette di crea tutte le permutazioni di una stringa. Questa procedura permette
 *   di creare tutti i possibili Gruppi di Teams
 *   
 * @param str			Stringa di input che permette di creare tutte le possibile combinazioni 		
 * @return List<String>	Array contenente tutte le possibile combinazioni della Stringa di Input  	
 *--------------------*/

	public List<String> findPermutations(String str)
    {
 
        // crea un ArrayList vuoto per memorizzare le permutazioni (parziali).
        List<String> partial = new ArrayList<>();
        String sTemp = "";
        int    nInd=0;
        
        // caso base
        if (str == null || str.length() == 0) {
            return partial;
        }

        // inizializza la lista con il primo carattere della stringa
        partial.add(String.valueOf(str.charAt(0)));
 
        // fare per ogni carattere della stringa specificata
        for (int i = 1; i < str.length(); i++)
        {
            // considera una per una le permutazioni parziali costruite in precedenza
 
            // (itera indietro per evitare ConcurrentModificationException)
            for (int j = partial.size() - 1; j >= 0 ; j--)
            {
                // rimuove la permutazione parziale corrente da ArrayList
                String s = partial.remove(j);
 
                // Inserisce il carattere successivo della stringa specificata
                // possibili posizioni di permutazione parziale corrente. Quindi
                // inserisce ciascuna di queste stringhe di nuova costruzione nell'elenco
 
                for (int k = 0; k <= s.length(); k++)
                {
                    // Consiglio: usa StringBuilder per la concatenazione
                	sTemp = s.substring(0, k) + str.charAt(i) + s.substring(k);
                    partial.add(sTemp);
                }
            }
        }
 
        
        nInd=partial.size()-1;
               
        sTemp =partial.get(nInd);
        
        while(nInd>0) {
        	
        	if ( isOrdered(sTemp) == false) {
        		partial.remove(nInd);
        	}
        	
        	nInd--;
        	sTemp =partial.get(nInd);
        	
        }

//	      System.out.println(partial);
        
        return partial;
    }
	
/*--------------------
 * isNumeric permette di verificare se una stringa contiene un numero
 * @param s 		Stringa di Input
 * @return boolean  true se la stringa contiene un numero	
 *--------------------*/
	public boolean isNumeric(String s)
    {
 
 // se la stringa non contiene nulla allora false   
        if (s == null || s.equals("")) {
            return false;
        }
 
 // se la stringa contiene solo numeri allora true
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
		
/*--------------------*/
		
} // class Services

