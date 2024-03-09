import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SlotViewComponent } from './slot-view.component';

describe('SlotViewComponent', () => {
  let component: SlotViewComponent;
  let fixture: ComponentFixture<SlotViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SlotViewComponent]
    });
    fixture = TestBed.createComponent(SlotViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
