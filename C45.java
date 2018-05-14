import weka.core.Instances;
import weka.filters.Filter;
import weka.classifiers.trees.J48;
import weka.filters.unsupervised.attribute.Remove;
import weka.classifiers.Evaluation;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.awt.BorderLayout;

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
		System.out.println("Graph:\n\n"+tree.graph()+"\n\n\n\n");
		//System.out.println("Algorithm described in the Tree:\n\n"+tree.toSource()+"\n\n\n\n");

		//Evaluation
		//Using Cross-Validation
		Evaluation eval = new Evaluation(data);
		eval.crossValidateModel(tree, data, 10, new Random(1));
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));

		//Data Visualization
		// display classifier
		final javax.swing.JFrame jf = 
			new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
		jf.setSize(2000,2000);
		jf.getContentPane().setLayout(new BorderLayout());
		TreeVisualizer tv = new TreeVisualizer(null,
				tree.graph(),
				new PlaceNode2());
		jf.getContentPane().add(tv, BorderLayout.CENTER);
		jf.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				jf.dispose();
			}
		});

		jf.setVisible(true);
		tv.fitToScreen();
	}
}

