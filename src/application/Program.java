package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		Double slr = sc.nextDouble();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}

			System.out.println("Email of people whose salary is more than " + slr);
			
			List<String> emails = list.stream()
					.filter(p -> p.getsalary() > slr)
					.map(p -> p.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			emails.forEach(System.out::println);
			
			
			double sum = list.stream()
					.filter(p -> p.getName().toUpperCase().charAt(0) == 'M')
					.map(p -> p.getsalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
		
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}
