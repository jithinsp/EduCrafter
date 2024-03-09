import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent {

  ngOnInit() {
    this.loadAll();
  }

  title = 'Subjects';
  showCreateButton = true;
  showEditButton = true;
  showToggleButton = true;
  showDeleteButton = true;
  showViewButton = false;

  constructor(private academicsService: AcademicsService,
    private router: Router) { }

  list: any[] = [];
  headArray = [
    { 'Head': 'No', 'FieldName': 'no' },
    { 'Head': 'Subject', 'FieldName': 'name' },
    { 'Head': 'Description', 'FieldName': 'description' },
    { 'Head': 'Action', 'FieldName': '' }
  ];

  loadAll() {
    this.academicsService.getAllSubjects().subscribe((res) => {
      this.list = res;
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  edit(item: any) {
    const mode = 'edit';
    console.log(item.id);
    const id = item.id;
    this.router.navigate(['new-subject'], { queryParams: { id, mode } });
  }

  toggle(item: any) {
    this.academicsService.toggleSubjects(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  deletes(item: any) {
    this.academicsService.deleteSubjects(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  create() {
    this.router.navigateByUrl("new-subject");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }
}
