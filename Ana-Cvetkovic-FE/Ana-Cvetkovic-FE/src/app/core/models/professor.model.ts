import { Subject } from "./subject.model";

export interface Professor{
  id: number;
	adress?: string;
	email?: string;
	firstName: string;
	lastName: string;
  phone?: string;
  reelectionDate: Date;
	city?: City;
	title: Title;
  subjects?: Subject[];
}

export interface City{
  cityCode: number;
  name: string;
}

export interface Title{
  id: number;
  name: string;
}
