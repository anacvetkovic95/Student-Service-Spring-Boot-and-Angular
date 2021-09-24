import { Professor } from "./professor.model";

export interface Student{
  id: number;
	indexNumber: string;
	indexYear: number;
	firstName: string;
	lastName: string;
	email: string;
	adress: string;
	city: City;
	currentYearOfStudy: number;
}

export interface City{
  cityCode: number;
  name: string;
}
