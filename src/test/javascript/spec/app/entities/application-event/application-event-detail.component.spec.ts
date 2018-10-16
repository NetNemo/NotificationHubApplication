/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotificationHubTestModule } from '../../../test.module';
import { ApplicationEventDetailComponent } from 'app/entities/application-event/application-event-detail.component';
import { ApplicationEvent } from 'app/shared/model/application-event.model';

describe('Component Tests', () => {
    describe('ApplicationEvent Management Detail Component', () => {
        let comp: ApplicationEventDetailComponent;
        let fixture: ComponentFixture<ApplicationEventDetailComponent>;
        const route = ({ data: of({ applicationEvent: new ApplicationEvent('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [ApplicationEventDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ApplicationEventDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApplicationEventDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.applicationEvent).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
