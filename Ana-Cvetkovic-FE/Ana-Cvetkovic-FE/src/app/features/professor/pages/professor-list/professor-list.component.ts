import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpProfessorService } from 'src/app/service/http-professor.service';
import { ToastService } from 'src/app/service/toast.service';
import { ConfirmDialogComponent, SortableHeaderDirective, SortEvent } from 'src/app/shared';

const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})
export class ProfessorListComponent implements OnInit {

  professors: Professor[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 5;
  options = [5,10,15,20];


  constructor(
    private httpProfessor: HttpProfessorService,
    private router: Router,
    private modalService: NgbModal,
    private toastService: ToastService) { }

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;

  ngOnInit(): void {
    this.loadProfessors();
  }

  loadProfessors(){
    this.httpProfessor.getByPage(this.currentPage-1, this.pageSize).subscribe(
      response => {
        this.professors = response.content;
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
      this.professors = [...this.professors].sort((a, b) => {
      const res = compare(a[event.column], b[event.column]);
      return event.direction === 'desc' ? -res : res;
    });
    })
  }

  onPageChange(page: number) {
    this.currentPage = page;
    this.loadProfessors();
  }

  onPageSizeChange(event: any){
    this.pageSize = event.target.value;
  }

  openNewProfessorForm(){
    this.router.navigate(['/professor/professor-form'])
  }

  onDeleteClick(professor: Professor) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete subject <strong>${professor.firstName} ${professor.lastName}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting student';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedProfessor(professor)
    );
  }

  deleteSelectedProfessor(professor: Professor) {
    this.httpProfessor.deleteProfessor(professor).subscribe((response) => {
      this.toastService.show(
        'Professor Deleted ',
        { header: 'Deleting Professor', classname: 'bg-success text-light' }
      );
      this.loadProfessors();
    });
  }

}
