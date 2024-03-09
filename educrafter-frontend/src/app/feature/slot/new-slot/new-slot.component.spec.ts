import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSlotComponent } from './new-slot.component';

describe('NewSlotComponent', () => {
  let component: NewSlotComponent;
  let fixture: ComponentFixture<NewSlotComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewSlotComponent]
    });
    fixture = TestBed.createComponent(NewSlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
