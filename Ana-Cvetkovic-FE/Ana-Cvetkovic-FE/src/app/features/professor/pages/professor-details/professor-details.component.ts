import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpProfessorService } from 'src/app/service/http-professor.service';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css']
})
export class ProfessorDetailsComponent implements OnInit {

  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  professor: Professor;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private httpProfessor: HttpProfessorService) { }

    ngOnInit(): void {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadProfessor(id);
    }

    ngOnDestroy() {
      this.destroy$.next(true);
    }

    loadProfessor(id: number) {
      this.httpProfessor.getById(id)
      .pipe(
        takeUntil(this.destroy$)
      )
      .subscribe( professor => {
        this.professor = professor
      });
    }
}
