# super simple makefile
# call it using 'make NAME=name_of_code_file_without_extension'
# (assumes a .java extension)
NAME = "Doodle"

all:
	@echo "Compiling..."
	javac *.java model/*.java view/*.java controller/*.java

run: all
	@echo "Running..."
	java Doodle

clean:
	rm -rf *.class
	rm -rf view/*.class
	rm -rf model/*.class
	rm -rf controller/*.class
