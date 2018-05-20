COMP=javac
RUN=java
FLAGS_COMP=-cp ./weka.jar
FLAGS_RUN=-cp .:weka.jar C45 HTRU_2.arff
OPTION = 1
all: c45 

c45: C45.java
	$(COMP) $(FLAGS_COMP) C45.java

run: C45.class
	$(RUN) $(FLAGS_RUN) $(OPTION)

clean:
	rm *.class
