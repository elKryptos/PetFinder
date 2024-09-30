import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindPetsComponent } from './find-pets.component';

describe('FindPetsComponent', () => {
  let component: FindPetsComponent;
  let fixture: ComponentFixture<FindPetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FindPetsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FindPetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
