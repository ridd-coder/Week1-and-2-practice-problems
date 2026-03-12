import java.util.*;

class PlagiarismDetector {

    // n-gram -> documents containing it
    private HashMap<String, Set<String>> index = new HashMap<>();

    int N = 5; // 5-gram

    // add document to database
    public void addDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        for (String gram : ngrams) {
            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(docId);
        }
    }

    // analyze a new document
    public void analyzeDocument(String docId, String text) {

        List<String> ngrams = generateNGrams(text);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {
            if (index.containsKey(gram)) {

                for (String otherDoc : index.get(gram)) {
                    matchCount.put(otherDoc,
                            matchCount.getOrDefault(otherDoc, 0) + 1);
                }
            }
        }

        System.out.println("Extracted " + ngrams.size() + " n-grams\n");

        for (String doc : matchCount.keySet()) {

            int matches = matchCount.get(doc);
            double similarity = (matches * 100.0) / ngrams.size();

            System.out.println("Found " + matches +
                    " matching n-grams with \"" + doc + "\"");

            System.out.println("Similarity: " +
                    String.format("%.2f", similarity) + "%");

            if (similarity > 50)
                System.out.println("⚠ PLAGIARISM DETECTED\n");
            else if (similarity > 10)
                System.out.println("(Suspicious)\n");
        }
    }

    // generate n-grams
    private List<String> generateNGrams(String text) {

        String[] words = text.split("\\s+");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < N; j++) {
                gram.append(words[i + j]).append(" ");
            }

            grams.add(gram.toString().trim());
        }

        return grams;
    }
}

public class Problem4 {

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        String doc1 = "machine learning is a field of artificial intelligence that uses data";
        String doc2 = "machine learning is a field of artificial intelligence used in data science";

        detector.addDocument("essay_089.txt", doc1);

        detector.analyzeDocument("essay_123.txt", doc2);
    }
}

