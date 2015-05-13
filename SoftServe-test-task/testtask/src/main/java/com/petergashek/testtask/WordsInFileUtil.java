package com.petergashek.testtask;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public final class WordsInFileUtil
{

   private WordsInFileUtil()
   {
   }

   public static Map<String, Integer> getWords( String nameOfFile ) // Method to get words from file

   {

      FileInputStream fiStream = null;
      DataInputStream diStream = null;
      BufferedReader bReader = null;
      String targetLine = null;

      Map<String, Integer> wordMap = new HashMap<String, Integer>();

      try
      {
         fiStream = new FileInputStream(nameOfFile);
         diStream = new DataInputStream(fiStream);
         bReader = new BufferedReader(new InputStreamReader(diStream));

         while ((targetLine = bReader.readLine()) != null)
         {
            StringTokenizer sTokenizer = new StringTokenizer(targetLine, " ");

            while (sTokenizer.hasMoreTokens())
            {
               String buffer = sTokenizer.nextToken().toLowerCase(); // to prevent case sensitive collisions

               if (wordMap.containsKey(buffer))
               {
                  wordMap.put(buffer, wordMap.get(buffer) + 1);
               }
               else
               {
                  wordMap.put(buffer, 1);
               }
            }
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File Not found.");
      }
      catch (IOException e)
      {
         System.out.println("Invalid file.");
      }
      finally
      {

         try
         {
            if (bReader != null)
               bReader.close();
         }
         catch (Exception ex)
         {
         }
      }
      return wordMap;
   }

   public static List<Entry<String, Integer>> getMaxOccurence( Map<String, Integer> wordMap ) // Method that encapsulates comparative logic
   {

      Set<Entry<String, Integer>> wordSet = wordMap.entrySet();
      List<Entry<String, Integer>> resulsList = new ArrayList<Entry<String, Integer>>(wordSet);
      Collections.sort(resulsList, new Comparator<Map.Entry<String, Integer>>() // here we redefining comparator for our purposes

      {
         public int compare( Map.Entry<String, Integer> occurence, Map.Entry<String, Integer> nextOccurence )
         {
            return (nextOccurence.getValue()).compareTo(occurence.getValue());// if we switch places of occurrences we get acceding or decceding output
         }
      });
      return resulsList;
   }

   public static List<String> topFiveOutput( String path ) // Method for endUser to run
   {

      Map<String, Integer> wordMap = getWords(path);

      List<Entry<String, Integer>> resultList = getMaxOccurence(wordMap);
      List<String> returnedResult = new ArrayList<String>(5);
      int counter = 0;
      for (Map.Entry<String, Integer> entry : resultList)
      {

         returnedResult.add(entry.getKey() + " occured " + entry.getValue() + " time(s)");
         counter++;
         if (counter == 5) // not very common and loved but understandable solution to get top five limit
         { 
            break;
         }
      }

      return returnedResult;

   }

}