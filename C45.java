import weka.core.Instances;
import weka.filters.Filter;
import weka.classifiers.trees.J48;
import weka.filters.unsupervised.attribute.Remove;
import weka.classifiers.Evaluation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

class C45 {

	public static void main(String[] args)  throws Exception {
		///Reading ARFF File
		System.out.println("teste");
		BufferedReader reader = new BufferedReader( new FileReader ("./HTRU_2.arff"));
		Instances data = new Instances(reader);
		reader.close();
		data.setClassIndex(data.numAttributes() - 1); //Atrribute that indicate the result

		//Pré-processing Database
		//String[] options = new String[2];
		//options[0] = "-R";                                    // "range"
		//options[1] = "1";                                     // first attribute
		//Remove remove = new Remove();                         // new instance of filter
		//remove.setOptions(options);                           // set options
		//remove.setInputFormat(data);                          // inform filter about dataset **AFTER** setting options
		//Instances newData = Filter.useFilter(data, remove);   // apply filter

		//Classifier
		String[] options = new String[1];
		options[0] = "-U";            // unpruned tree
		J48 tree = new J48();         // new instance of tree
		tree.setOptions(options);     // set the options
		tree.buildClassifier(data);   // build classifier

		//Evaluation
		//Using Cross-Validation
		Evaluation eval = new Evaluation(data);
		eval.crossValidateModel(tree, data, 10, new Random(1));
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));
	}
}

