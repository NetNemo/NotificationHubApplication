/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { EventAttachDetailComponent } from 'app/entities/event-attach/event-attach-detail.component';
import { EventAttach } from 'app/shared/model/event-attach.model';

describe('Component Tests', () => {
    describe('EventAttach Management Detail Component', () => {
        let comp: EventAttachDetailComponent;
        let fixture: ComponentFixture<EventAttachDetailComponent>;
        const route = ({ data: of({ eventAttach: new EventAttach('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [EventAttachDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EventAttachDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventAttachDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.eventAttach).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
