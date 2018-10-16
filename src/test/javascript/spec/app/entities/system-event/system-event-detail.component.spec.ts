/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { SystemEventDetailComponent } from 'app/entities/system-event/system-event-detail.component';
import { SystemEvent } from 'app/shared/model/system-event.model';

describe('Component Tests', () => {
    describe('SystemEvent Management Detail Component', () => {
        let comp: SystemEventDetailComponent;
        let fixture: ComponentFixture<SystemEventDetailComponent>;
        const route = ({ data: of({ systemEvent: new SystemEvent('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [SystemEventDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SystemEventDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemEventDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.systemEvent).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
