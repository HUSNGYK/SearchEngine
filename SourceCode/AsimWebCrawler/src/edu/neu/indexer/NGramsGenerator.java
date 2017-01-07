package edu.neu.indexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import edu.neu.pojos.DocumentDetails;
import edu.neu.pojos.IndexEntry;

public class NGramsGenerator {

/*	public static void main(String args[]) {
		String text = "My name is asim khan#####.";
		// StringTokenizer s = new StringTokenizer(text);

		String[] tokens = text.split("\\s+"); // split sentence into tokens

		int N = 3;
		List<String> ngramList = new ArrayList<String>();

		for (int k = 0; k < (tokens.length - N + 1); k++) {
			String s = "";
			int start = k;
			int end = k + N;
			for (int j = start; j < end; j++) {
				s = s + " " + tokens[j];
			}
			// Add n-gram to a list
			ngramList.add(s);
		}
		System.out.println(ngramList);

	}*/

	public static void createNGrams(DocumentDetails doc, int ngram,
			Map<String, IndexEntry> indexMap) {

		String[] tokens = doc.getDocText().split("\\s+"); // split sentence into
															// tokens
		List<String> ngramList = new ArrayList<String>();
		for (int k = 0; k < (tokens.length - ngram + 1); k++) {
			String str = "";
			int start = k;
			int end = k + ngram;
			for (int j = start; j < end; j++) {
				str += " " + tokens[j].trim();
			}
			// Add n-gram to a Map
			if (str!=null && !str.trim().isEmpty()) { 
				str=str.trim();
			if (indexMap.containsKey(str)) {
				
				IndexEntry ie = indexMap.get(str);
				ie.addDocFrequencyByOne();
				
				if (ie.getDocs()!=null && !ie.getDocs().isEmpty() && ie.getDocs().contains(doc)) {
					DocumentDetails docDetails = ie.getDocs().get(ie.getDocs().indexOf(doc));
					docDetails.addTermFrequencyByOne();
				}
				else {
					DocumentDetails docDetails = new DocumentDetails();
					docDetails.addTermFrequencyByOne();
					docDetails.setDocID(doc.getDocID());
					docDetails.setDocName(doc.getDocName());
					ie.getDocs().add(docDetails);
					
				}
			}
			else {
				IndexEntry i = new IndexEntry();
				i.setIndexName(str);
				i.addDocFrequencyByOne();
				DocumentDetails d = new DocumentDetails();
				d.addTermFrequencyByOne();
				d.setDocID(doc.getDocID());
				d.setDocName(doc.getDocName());
				i.addDocFrequencyByOne();
				i.getDocs().add(d);
				indexMap.put(str, i);
			}
			ngramList.add(str);
		}
			 
			
		}
		
	}

}
