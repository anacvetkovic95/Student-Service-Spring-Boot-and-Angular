import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Student } from 'src/app/core/models/student.model';
import { HttpStudentService } from 'src/app/service/http-student.service';
import { ToastService } from 'src/app/service/toast.service';
import { ConfirmDialogComponent, SortableHeaderDirective, SortEvent } from 'src/app/shared';

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 5;
  options = [ 5, 10, 15, 20];

  constructor(
    private httpStudent: HttpStudentService,
    private router: Router,
    private modalService: NgbModal,
    private toastService: ToastService) { }

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(){
    this.httpStudent.getByPage(this.currentPage - 1, this.pageSize).subscribe(
      response => {
        this.students = response.content;
        this.totalItems = response.totalElements;
        this.pageSize = response.size;
        this.currentPage =  response.number + 1;
      }
    )
  }

  onSort(event: SortEvent) {
    this.headers.forEach( header => {
      if (header.sortable !== event.column) {
        header.direction = '';
      }
      this.students = [...this.students].sort((a, b) => {
      const res = compare(a[event.column], b[event.column]);
      return event.direction === 'desc' ? -res : res;
    });
    })
  }

  onPageChange(page: number) {
    this.currentPage = page;
    this.loadStudents();
  }

  onPageSizeChange(event: any){
    this.pageSize = event.target.value;
  }

  openNewStudentForm(){
    this.router.navigate(['/student/student-form'])
  }

  onDeleteClick(student: Student) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete student <strong>${student.firstName} ${student.lastName}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting student';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedStudent(student)
    );
  }

  deleteSelectedStudent(student: Student) {
    this.httpStudent.deleteStudent(student).subscribe((response) => {
      this.toastService.show(
        'Student Deleted ',
        { header: 'Deleting student', classname: 'bg-success text-light' }
      );
      this.loadStudents();
    });
  }
}
