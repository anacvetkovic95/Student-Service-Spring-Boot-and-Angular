import { Professor } from "./professor.model";

export interface Subject{
  id: number;
	name: string;
	description?: string;
	noOfESPB: number;
	yearOfStudy: number;
	semester: Semester;
  engagedProfessors?: Professor[];
}

export enum Semester{
  summer = "Summer",
  winter = "Winter"
}
