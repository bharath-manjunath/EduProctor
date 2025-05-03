import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationIntroComponent } from './examination-intro.component';

describe('ExaminationIntroComponent', () => {
  let component: ExaminationIntroComponent;
  let fixture: ComponentFixture<ExaminationIntroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationIntroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExaminationIntroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
