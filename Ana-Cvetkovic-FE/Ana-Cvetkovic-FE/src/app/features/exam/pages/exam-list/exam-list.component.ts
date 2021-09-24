import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exam } from 'src/app/core/models/Exam';
import { HttpExamService } from 'src/app/service/http-exam.service';
import { ToastService } from 'src/app/service/toast.service';
import { ConfirmDialogComponent, SortableHeaderDirective, SortEvent } from 'src/app/shared';

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css']
})
export class ExamListComponent implements OnInit {

  exams: Exam[];

  constructor(
    private httpExam: HttpExamService,
    private router: Router,
    private modalService: NgbModal,
    private toastService: ToastService) { }

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadExams();
  }

  loadExams(){
    this.httpExam.getAll().subscribe(
      response => {
        this.exams = response;
      }
    )
  }

  onSort(event: SortEvent) {
    this.headers.forEach( header => {
      if (header.sortable !== event.column) {
        header.direction = '';
      }
      this.exams = [...this.exams].sort((a, b) => {
      const res = compare(a[event.column], b[event.column]);
      return event.direction === 'desc' ? res : -res;
    });
    })
  }

  openNewExamForm(){
    this.router.navigate(['/exam/exam-form'])
  }

  onDeleteClick(exam: Exam) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete subject <strong>${exam.subject.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting exam';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedExam(exam)
    );
  }

  deleteSelectedExam(exam: Exam) {
    this.httpExam.deleteExam(exam).subscribe((response) => {
      this.toastService.show(
        'Student Deleted ',
        { header: 'Deleting exam', classname: 'bg-success text-light' }
      );
      this.loadExams();
    });
  }

}
