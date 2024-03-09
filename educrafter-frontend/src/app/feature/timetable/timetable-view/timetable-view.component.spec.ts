import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimetableViewComponent } from './timetable-view.component';

describe('TimetableViewComponent', () => {
  let component: TimetableViewComponent;
  let fixture: ComponentFixture<TimetableViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TimetableViewComponent]
    });
    fixture = TestBed.createComponent(TimetableViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
