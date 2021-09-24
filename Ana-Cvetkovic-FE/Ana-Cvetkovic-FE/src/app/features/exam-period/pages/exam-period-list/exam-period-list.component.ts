import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ExamPeriod } from 'src/app/core/models/ExamPeriod';
import { HttpExamPeriodService } from 'src/app/service/http-exam-period.service';
import { ToastService } from 'src/app/service/toast.service';
import { ConfirmDialogComponent, SortableHeaderDirective, SortEvent } from 'src/app/shared';

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

@Component({
  selector: 'app-exam-period-list',
  templateUrl: './exam-period-list.component.html',
  styleUrls: ['./exam-period-list.component.css']
})
export class ExamPeriodListComponent implements OnInit {

  examPeriods: ExamPeriod[];

  constructor(
    private httpExamPeriod: HttpExamPeriodService,
    private router: Router,
    private modalService: NgbModal,
    private toastService: ToastService) { }

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadExamPeriod();
  }

  loadExamPeriod(){
    this.httpExamPeriod.getAll().subscribe(
      response => {
        this.examPeriods = response;
      }
    )
  }

  onSort(event: SortEvent) {
    this.headers.forEach( header => {
      if (header.sortable !== event.column) {
        header.direction = '';
      }
      this.examPeriods = [...this.examPeriods].sort((a, b) => {
      const res = compare(a[event.column], b[event.column]);
      return event.direction === 'desc' ? res : -res;
    });
    })
  }

  openNewExamPeriodForm(){
    this.router.navigate(['/exam-period/exam-period-form'])
  }

  onDeleteClick(examPeriod: ExamPeriod) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete subject <strong>${examPeriod.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting subject';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedExamPeriod(examPeriod)
    );
  }

  deleteSelectedExamPeriod(examPeriod: ExamPeriod) {
    this.httpExamPeriod.deleteExamPeriod(examPeriod).subscribe((response) => {
      this.toastService.show(
        'Exam period Deleted ',
        { header: 'Deleting exam period', classname: 'bg-success text-light' }
      );
      this.loadExamPeriod();
    });
  }

}
