package com.quiqbook.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiqbook.Response.ApiResponseService;
import com.quiqbook.models.Session;
import com.quiqbook.service.SessionService;
import com.quiqbook.web.rest.errors.BadRequestAlertException;

import springfox.documentation.annotations.ApiIgnore;

/**
 * REST controller for managing {@link com.quiqbook.domain.Session}.
 */
@RestController
@RequestMapping("/qbkuser")
@ApiIgnore
public class SessionResource {

    private final Logger log = LoggerFactory.getLogger(SessionResource.class);

    private static final String ENTITY_NAME = "session";

    @Autowired
	ApiResponseService apiResponseService;

    private final SessionService sessionService;

    public SessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * {@code POST  /sessions} : Create a new session.
     *
     * @param session the session to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new session, or with status {@code 400 (Bad Request)} if the session has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sessions")
    @PreAuthorize("@authentication.hasPermisionToAdmin('','','SESSION')")
    public Object createSession(@RequestBody Session session) throws URISyntaxException {
        log.debug("REST request to save Session : {}", session);
        if (session.getSessionId() != null) {
            throw new BadRequestAlertException("A new session cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Session result = sessionService.save(session);
        return apiResponseService.success("Session created successfully", result);
    }

    /**
     * {@code PUT  /sessions} : Updates an existing session.
     *
     * @param session the session to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated session,
     * or with status {@code 400 (Bad Request)} if the session is not valid,
     * or with status {@code 500 (Internal Server Error)} if the session couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sessions")
    @PreAuthorize("@authentication.hasPermisionToAdmin('','','SESSION')")
    public Object updateSession(@RequestBody Session session) throws URISyntaxException {
        log.debug("REST request to update Session : {}", session);
        if (session.getSessionId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Session result = sessionService.save(session);
        return apiResponseService.success("Session udated successfully", result);
    }

    /**
     * {@code GET  /sessions} : get all the sessions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sessions in body.
     */
    @GetMapping("/sessions")
    public Object getAllSessions() {
        log.debug("REST request to get all Sessions");
        List<Session> session = sessionService.findAll();
        
        if (session.isEmpty()) {
			return apiResponseService.failure(400, "session not found");
		}
		return apiResponseService.success("session found successfully", session);
    }

    /**
     * {@code GET  /sessions/:id} : get the "id" session.
     *
     * @param id the id of the session to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the session, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sessions/{id}")
    public Object getSession(@PathVariable Long id) {
        log.debug("REST request to get Session : {}", id);
        Optional<Session> session = sessionService.findOne(id);
        if (!session.isPresent()) {
			return apiResponseService.failure(400, "Session not found");
		}
		return apiResponseService.success("session found successfully", session);
    }

    /**
     * {@code DELETE  /sessions/:id} : delete the "id" session.
     *
     * @param id the id of the session to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sessions/{id}")
    @PreAuthorize("@authentication.hasPermisionToAdmin('','','SESSION')")
    public Object deleteSession(@PathVariable Long id) {
        log.debug("REST request to delete Session : {}", id);
        try {
        	sessionService.delete(id);
			return apiResponseService.success("Session deleted successfully", null);
		} catch (Exception e) {
			return apiResponseService.failure(400, "Session not deleted successfully");
		}
    }
}
