import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ObjectInfoPipe } from './pipes';
import { SortableHeaderDirective } from './directives/sortable-header.directive';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { GlobalToastComponent } from '.';


@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    ObjectInfoPipe,
    SortableHeaderDirective,
    ConfirmDialogComponent,
    GlobalToastComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    NgbModule
  ],
  exports:[
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    NgbModule,

    HeaderComponent,
    FooterComponent,
    ObjectInfoPipe,
    SortableHeaderDirective,
    GlobalToastComponent
  ]
})
export class SharedModule { }
