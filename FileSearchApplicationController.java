import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class FileSearchApplicationController {
    
    // Declare a FileChooser object to use for file selection
    final FileChooser fileChooser = new FileChooser();
    
    // Declare a File object to store the FileChooser selection
    private File file;
    
    // Declare a HashMapBackedSimpleMap using Strings as keys, and
    // a set of Integers as the values
    BinaryTree<String, Set<Integer>> map = 
                new BinaryTree<>();
    
    // FXML Declaration for the chooseFileButton
    @FXML
    private Button chooseFileButton;
    
    // FXML Declaration for the searchForTextField
    @FXML
    private TextField searchForTextField;
    
    // FXML Declaration for the chooseFileTextField
    @FXML
    private TextField chosenFileTextField;
    
    // Function definition for the Choose File button
    @FXML
    void chooseFileButtonPressed(ActionEvent event) {
        
        // Add extension filters to the fileChooser to only allow
        // .txt files to be selected
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        // Assign the user's selection to the File variable file
        file = fileChooser.showOpenDialog(searchForTextField
                               .getScene()
                               .getWindow());
        
        // If the file exists, run the following code
        if (file != null) {
            
            // Print the file name in the chosenFileTextField box
            chosenFileTextField.replaceSelection(file.getName());
            
            // Try/Catch block will attempt to read input from the file
            try (Scanner scanner = new Scanner(file)){
                
                // Declare an integer variable to hold the line number 
                int lineCount = 0;
                
                // While loop will step through each line in the file until
                // it reaches the end
                while (scanner.hasNextLine()){
                    
                    // Increment the line counter
                    lineCount++;
                    
                    // Declare a String variable to hold the line of input
                    String line = scanner.nextLine();
                    
                    // Declare an Array of Strings to hold the words for the
                    // line of input and split the line by whitespace
                    String lineWords[] = line.split("\\s");
                    
                    // Declare a List of Strings to hold formatted words
                    List<String> words = Arrays.stream(lineWords)
                            
                            // Convert all letters to lower case
                            .map(i -> i.toLowerCase())
                            
                            // Remove all punctuation 
                            .map(i -> i.replaceAll("\\W", ""))
                            
                            // Accumulate formatted words into the List
                            .collect(Collectors.toList());                    
                    
                    // For loop steps through each token in the list and adds
                    // it to the map
                    for (String key : words){
                        
                        // Declare a set of Integers to hold the line number
                        // for each word and assign it the value for which the
                        // specific key is mapped in the HashMapBackedSimpleMap
                        Set<Integer> lineNumbers = map.get(key);
                        
                        // If the value returned is null, the map does not
                        // contain a mapping for the key and it must be established
                        if (lineNumbers == null){
                            
                            // Declare a new set of Integers
                            Set<Integer> newKey = new HashSet<>();
                            
                            // Add the line position to the set for the new key
                            newKey.add(lineCount);
                            
                            // Associate the specified value for the line with
                            // this particular key in the map
                            map.put(key, newKey);
                        }// End if
                        
                        // If the value returned is not null, the mapping for
                        // the key already exists, and the set of line numbers
                        // only needs to be updated with the current line
                        else{
                            
                            // Add the current line counter to the set
                            lineNumbers.add(lineCount);
                            
                            // Associate the new set of line numbers with
                            // this particular key in the map
                            map.put(key, lineNumbers);
                        }// End else
                    }// End for
                }// End while
            }// End try
            
            // This catch block will execute if any exceptions are thrown when
            // reading input from the file and display an error message
            catch (Exception e){
                
                // Create a new Alert of type ERROR
                Alert alert = new Alert(Alert.AlertType.ERROR);
                
                // Set the title
                alert.setTitle("Error Message");
                
                // Set the Header Text
                alert.setHeaderText("Ooops!");
                
                // Set the text for the alert message
                alert.setContentText("Error reading the file!");
                
                // Specify the owner Window for the alert message 
                alert.initOwner(searchForTextField.getScene().getWindow());
                
                // Display the alert window
                alert.show();
            }// End catch
        }// End if
    }// End chooseFileButtonPressed
    
    // Function definition for searchButtonPressed
    @FXML
    void searchButtonPressed(ActionEvent event) {
        
        // If the map is empty, print an error message to prompt user
        // to select a file
        if (map.isEmpty()){
            
            // Comments identical to the Alert above
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Ooops!");
            alert.setContentText("Please select a valid text file!");
            alert.initOwner(searchForTextField.getScene().getWindow());
            alert.show();
        }// End if
        
        // If the searchForTextField is empty, print an error message to
        // prompt the user to enter a search word
        else if (searchForTextField.getText().isEmpty()){
            
            // Comments identical to the Alerts above
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Ooops!");
            alert.setContentText("Please enter a word to search for!");
            alert.initOwner(searchForTextField.getScene().getWindow());
            alert.show();
        }// End else if
        
        // If neither the map nor the searchForTextField is empty, proceed
        else {
            
            // Declare a String and assign it the value in searchForTextField
            // (converted to lowercase for easy comparison with our formatted
            // words in the map)
            String search = searchForTextField.getText().toLowerCase();
            
            // If the map contains the search value
            if (map.get(search) != null){
                
                // Declare a set of Integers and assign it the values in the
                // set of Integers stored in the map for this specific key
                Set<Integer> lineNumbers = map.get(search);
                
                // Declare a String variable to hold the output
                String output = "";
                
                // Range based for loop itterates through the values in lineNumbers
                for (Integer num : lineNumbers){
                    
                    // Try/Catch block will read input from the original file
                    try (Scanner scanner = new Scanner(file)){
                        
                        // Start the line number counter at one
                        Integer lineCount = 1;
                        
                        // While loop increments the line number of the original
                        // file until it reaches the correct position for the
                        // next value in lineNumbers
                        while(scanner.hasNext() && (lineCount < num)) {
                            scanner.nextLine();
                            lineCount++;
                        }// End while
                        
                        // Concatenate the new line from the original file into
                        // the String variable for output
                        output += ("\nLine " + num.toString() + ": "
                                + scanner.nextLine() + "\n");                          
                    }// End try
                    
                    // This catch block will execute if any exceptions are thrown when
                    // reading input from the file and display an error message
                    catch (Exception e){
                        
                        // Comments identical to the Alerts above
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error message");
                        alert.setHeaderText("Ooops!");
                        alert.setContentText("Error reading the file!");
                        alert.initOwner(searchForTextField.getScene().getWindow());
                        alert.show();
                    }// End catch
                }// End for
                
                // After all lines have been concatenated to output, display
                // the results in an alert window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText("The lines with your search word are:");
                alert.setContentText(output);
                alert.initOwner(searchForTextField.getScene().getWindow());
                alert.show();
            }// End if
            
            // If the map does not contain the search value, display the results
            else {
                
                // Comments identical to the Alerts above
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Search Results");
                alert.setHeaderText("Please Try Again!");
                alert.setContentText("The word you are searching "
                        + "for is not in the file.");
                alert.initOwner(searchForTextField.getScene().getWindow());
                alert.show();
            }// End else
        }// End else
    }// End searchButtonPressed
    
    // Function Definition clearButtonPressed
    @FXML
    void clearButtonPressed(ActionEvent event) {
        
        // Set the value for file to null
        file = null;
        
        // Clear the map of any previous values
        map.clear();
        
        // Clear the chosenFileTextField
        chosenFileTextField.clear();
        
        // Clear the searchForTextField
        searchForTextField.clear();
        
        // Shift focus to the chooseFileButton
        chooseFileButton.requestFocus();
    }// End clearButtonPressed
}// End FileSearchApplicationController
