NAME = "Doodle"

all:
	@echo "Compiling..."
	#javac a2/*.java a2/Model/*.java a2/View/*.java a2/Controller/*.java
	javac *.java Model/*.java View/*.java Controller/*.java

run: all
	@echo "Running..."
	#java a2/$(NAME)
	java $(NAME)

clean:
	#rm -rf a2/*.class
	#rm -rf a2/Model/*/*.class
	#rm -rf a2/View/*/*.class
	#rm -rf a2/Controller/*/*.class

	rm -rf *.class
	rm -rf Model/*.class
	rm -rf View/*.class
	rm -rf Controller/*.class