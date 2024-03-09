import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';

@Component({
  selector: 'app-timetable-view',
  templateUrl: './timetable-view.component.html',
  styleUrls: ['./timetable-view.component.css']
})
export class TimetableViewComponent {
  displayedColumns: string[] = ['Day', '9:30-10:15', '10:15-11:00', '11:15-12:00', '12:00-12:45', '12:45-01:30', '01:30-02:15', '02:15-03:00', '03:15-04:00'];

  days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'];

  subjects = [
    'MALAYALAM_I',
    'MALAYALAM_II',
    'ENGLISH',
    'HINDI',
    'SOCIAL_SCIENCE',
    'PHYSICS',
    'CHEMISTRY',
    'BIOLOGY',
    'MATHEMATICS',
    'IT',
  ];

  dataSource = this.generateRandomSchedule();

  generateRandomSchedule() {
    const schedule = [];

    for (const day of this.days) {
      const shuffledSubjects = this.shuffleArray([...this.subjects]);
      const daySchedule = { Day: day };

      for (let i = 1; i < this.displayedColumns.length; i++) {
        if (this.displayedColumns[i] !== '12:45-01:30') {
          daySchedule[this.displayedColumns[i]] = shuffledSubjects[i - 1];
        } else {
          daySchedule[this.displayedColumns[i]] = '';
        }
      }

      schedule.push(daySchedule);
    }

    return schedule;
  }

  shuffleArray(array: any[]) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

//NEW
  ngOnInit() {
    this.loadAll();
  }

  title = 'Timetable';
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
    { 'Head': 'Class', 'FieldName': 'classes' },
    { 'Head': 'Subjects', 'FieldName': 'subjects' },
    { 'Head': 'Slot', 'FieldName': 'slot' },
    { 'Head': 'Teacher', 'FieldName': 'teacherId' },
    { 'Head': 'Action', 'FieldName': '' }
  ];

  loadAll() {
    this.academicsService.getAllTimetable().subscribe((res) => {
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
    this.router.navigate(['new-timetable'], { queryParams: { id, mode } });
  }

  toggle(item: any) {
    this.academicsService.toggleTimetable(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  deletes(item: any) {
    this.academicsService.deleteTimetable(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  create() {
    this.router.navigateByUrl("new-timetable");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }
}
