/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NotificationHubTestModule } from '../../../test.module';
import { LdapUserDeleteDialogComponent } from 'app/entities/ldap-user/ldap-user-delete-dialog.component';
import { LdapUserService } from 'app/entities/ldap-user/ldap-user.service';

describe('Component Tests', () => {
    describe('LdapUser Management Delete Component', () => {
        let comp: LdapUserDeleteDialogComponent;
        let fixture: ComponentFixture<LdapUserDeleteDialogComponent>;
        let service: LdapUserService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [NotificationHubTestModule],
                declarations: [LdapUserDeleteDialogComponent]
            })
                .overrideTemplate(LdapUserDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LdapUserDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LdapUserService);
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
