import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'src/app/core';
import { HttpSubjectService } from 'src/app/service/http-subject.service';
import { ToastService } from 'src/app/service/toast.service';
import { ConfirmDialogComponent, SortableHeaderDirective, SortEvent } from 'src/app/shared';

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 5;
  selectedOption: number;
  options = [5, 10, 15, 20];

  constructor(
    private httpSubject: HttpSubjectService,
    private router: Router,
    private modalService: NgbModal,
    private toastService: ToastService) { }

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadSubject();
  }

  loadSubject(){
    this.httpSubject.getByPage(this.currentPage-1, this.pageSize).subscribe(
      response => {
        this.subjects = response.content;
        this.totalItems = response.totalElements;
        this.pageSize = response.size;
        this.currentPage =  response.number+1;
      }
    )
  }

  onSort(event: SortEvent) {
    this.headers.forEach( header => {
      if (header.sortable !== event.column) {
        header.direction = '';
      }
      this.subjects = [...this.subjects].sort((a, b) => {
      const res = compare(a[event.column], b[event.column]);
      return event.direction === 'desc' ? -res : res;
    });
    })
  }

  onPageChange(page: number) {
    this.currentPage = page;
    this.loadSubject();
  }

  openNewSubjectForm(){
    this.router.navigate(['/subject/subject-form'])
  }

  onDeleteClick(subject: Subject) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete subject <strong>${subject.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting subject';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedCity(subject)
    );
  }

  deleteSelectedCity(subject: Subject) {
    this.httpSubject.deleteSubject(subject).subscribe((response) => {
      this.toastService.show(
        'City Deleted ',
        { header: 'Deleting subject', classname: 'bg-success text-light' }
      );
      this.loadSubject();
    });
  }

}
