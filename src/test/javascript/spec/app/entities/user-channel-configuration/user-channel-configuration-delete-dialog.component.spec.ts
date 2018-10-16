/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NotificationHubTestModule } from '../../../test.module';
import { UserChannelConfigurationDeleteDialogComponent } from 'app/entities/user-channel-configuration/user-channel-configuration-delete-dialog.component';
import { UserChannelConfigurationService } from 'app/entities/user-channel-configuration/user-channel-configuration.service';

describe('Component Tests', () => {
    describe('UserChannelConfiguration Management Delete Component', () => {
        let comp: UserChannelConfigurationDeleteDialogComponent;
        let fixture: ComponentFixture<UserChannelConfigurationDeleteDialogComponent>;
        let service: UserChannelConfigurationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [UserChannelConfigurationDeleteDialogComponent]
            })
                .overrideTemplate(UserChannelConfigurationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UserChannelConfigurationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserChannelConfigurationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
