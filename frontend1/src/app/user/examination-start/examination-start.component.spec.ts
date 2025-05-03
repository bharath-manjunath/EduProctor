import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationStartComponent } from './examination-start.component';

describe('ExaminationStartComponent', () => {
  let component: ExaminationStartComponent;
  let fixture: ComponentFixture<ExaminationStartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationStartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExaminationStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
