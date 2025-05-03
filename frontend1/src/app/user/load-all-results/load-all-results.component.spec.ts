import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadAllResultsComponent } from './load-all-results.component';

describe('LoadAllResultsComponent', () => {
  let component: LoadAllResultsComponent;
  let fixture: ComponentFixture<LoadAllResultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoadAllResultsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoadAllResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
