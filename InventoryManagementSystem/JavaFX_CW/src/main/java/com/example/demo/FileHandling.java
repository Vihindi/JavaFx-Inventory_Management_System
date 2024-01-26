package com.example.demo;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.time.LocalDate;
import java.util.*;



public class FileHandling {
    private Window stage;

    public ReadfileResult readItemDetails() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("E:\\1st year\\sem 2\\Java\\CW\\CW_20\\JavaFX_CW\\src\\main\\java\\com\\example\\demo\\TextFiles\\ItemDetails.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Map<String, List<Object>> hashmap = createHashMap();

        int NoOfLines = 0;
        while (scanner.hasNextLine()) {
            NoOfLines++;
            String item = scanner.nextLine();
            String[] column = item.split(",");

            if (column.length == 7) {
                String itemCode = column[0].trim();
                String itemName = column[1].trim();
                String itemBrand = column[2].trim();
                double price = Double.parseDouble(column[3].trim());
                int quantity = Integer.parseInt(column[4].trim());
                String category = column[5].trim();
                LocalDate date = LocalDate.parse(column[6].trim());

                hashmap.get("col1").add(itemCode);
                hashmap.get("col2").add(itemName);
                hashmap.get("col3").add(itemBrand);
                hashmap.get("col4").add(price);
                hashmap.get("col5").add(quantity);
                hashmap.get("col6").add(category);
                hashmap.get("col7").add(date);
            }
        }

        return new ReadfileResult(hashmap, NoOfLines);
    }

    public Map<String, List<Object>> createHashMap() {
        Map<String, List<Object>> hashmap = new LinkedHashMap<>();

        // Initialize empty ArrayLists for each key
        hashmap.put("col1", new ArrayList<>());
        hashmap.put("col2", new ArrayList<>());
        hashmap.put("col3", new ArrayList<>());
        hashmap.put("col4", new ArrayList<>());
        hashmap.put("col5", new ArrayList<>());
        hashmap.put("col6", new ArrayList<>());
        hashmap.put("col7", new ArrayList<>());

        return hashmap;
    }

    public ReadfileResult ReadDealerDetails() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("E:\\1st year\\sem 2\\Java\\CW\\CW_20\\JavaFX_CW\\src\\main\\java\\com\\example\\demo\\TextFiles\\Dealers.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Map<String, List<Object>> hashmap = createHashMap();
        int count = 0;
        int NoOfLines = 0;
        while (scanner.hasNextLine()) {
            NoOfLines++;
            String item = scanner.nextLine();
            String[] column = item.split("\\|");

            System.out.println(Arrays.toString(column));
            System.out.println(column.length);
            if (column.length == 7) {

                String dealerID = column[0].trim();
                String dealerName = column[1].trim();
                String contactNo = column[2].trim();
                String location = column[3].trim();


                hashmap.get("col1").add(dealerID);
                hashmap.get("col2").add(dealerName);
                hashmap.get("col3").add(contactNo);
                hashmap.get("col4").add(location);
                count++;
            }
        }
        return new ReadfileResult(hashmap, NoOfLines);
    }

    public void SavingItems() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\1st year\\sem 2\\Java\\CW\\CW_20\\JavaFX_CW\\src\\main\\java\\com\\example\\demo\\TextFiles\\ItemDetails.txt"));

        for (Items item : ItemsController.list) {
            // Assuming Items has name and quantity properties
            writer.write(item.getItemCode() + "," + item.getItemName() + "," + item.getItemBrand() + "," + item.getPrice() + "," + item.getQuantity() + "," + item.getCategory() + "," + item.getDate());
            writer.newLine();
        }
        writer.close();
        System.out.println("Data saved to items.txt");


    }


    public static Image selectImage(Stage stage) {
        // Create a FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        //fileChooser.setTitle("Open Image File");
        // Set the file extension filter (optional)
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show the dialog and wait for user selection
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Load the selected image and return it
            return new Image(selectedFile.toURI().toString());
        }

        // Return null if no file was selected
        return null;
    }

    public ReadfileResult DealerItems(String selectedID) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("E:\\1st year\\sem 2\\Java\\CW\\CW_20\\JavaFX_CW\\src\\main\\java\\com\\example\\demo\\TextFiles\\Dealers_Items.txt"));
            String line;
            Map<String, List<Object>> hashmap = createHashMap();
            int NoOfLines = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] column = line.split("\\|");
                if (column.length > 0) {
                    String dealerId = column[0].trim();
                    if (dealerId.equals(selectedID)) {
                        NoOfLines++;
                        System.out.println(Arrays.toString(column));
                        System.out.println(column.length);
                        if (column.length == 5) {
                            String itemName = column[1].trim();
                            String itemBrand = column[2].trim();
                            double price = Double.parseDouble(column[3].trim());
                            int quantity = Integer.parseInt(column[4].trim());


                            hashmap.get("col1").add(itemName);
                            hashmap.get("col2").add(itemBrand);
                            hashmap.get("col3").add(price);
                            hashmap.get("col4").add(quantity);

                        }

                    }

                }
            }
            System.out.println(NoOfLines);
            return new ReadfileResult(hashmap, NoOfLines);

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}


//    @FXML
//    Button button = new Button("Open a file");
//    @FXML
//    Label label = new Label();
//    ImageView imgView = new ImageView();
//    @FXML
//    void button(ActionEvent e) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open a file");
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
//        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file","*.txt"));
//        // this is for saving a file. remove the setInitialFileName if you are opening a file
//        //fileChooser.setInitialFileName("Untitled");
//        //File selectedFile = fileChooser.showOpenDialog(stage);
//        Stage stage = (Stage) button.getScene().getWindow();
//        File selectedFile = fileChooser.showOpenDialog(stage);
//        if(selectedFile != null){

            // this is for saving a file
            /*try {
                FileWriter fileWriter = new FileWriter(selectedFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write("Learning how to use the JavaFX FileChooser");
                writer.close();
                System.out.println("The file has been saved in "+ selectedFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/

            //this is for opening a file
//            label.setText(selectedFile.getName());
//            Image image = new Image(selectedFile.getPath());
//            imgView.setImage(image);


                /* This is for reading a text file
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line).append("\n");
                    }
                    System.out.println(stringBuilder.toString());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
//                }*/
//        }else{
//            System.out.println("No file has been selected");
//        }
//    }
//}





