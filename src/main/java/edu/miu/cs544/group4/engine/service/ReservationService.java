package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Reservation;
import edu.miu.cs544.group4.engine.service.request.CancelReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ConfirmReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ReservationRequest;
import edu.miu.cs544.group4.engine.service.response.PassengerReservationResponse;
import edu.miu.cs544.group4.engine.service.response.PriorDepartureReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResultResponse;

import java.util.List;

public interface ReservationService extends BaseReadWriteService<ReservationResponse, Reservation, Integer> {
  ReservationResultResponse makeReservation(ReservationRequest request);

  ReservationResultResponse makeAgentReservation(ReservationRequest request);

  ReservationResponse cancelReservationByCode(CancelReservationRequest request);

  ReservationResponse agentCancelReservationByCode(CancelReservationRequest request);

  List<ReservationResponse> getAllReservationsByEmail(String email);

  List<ReservationResponse> getAgentReservationsByEmail(String email);

  List<PassengerReservationResponse> getAllAgentPassengersAndTheirReservations(String email);

  ReservationResponse getReservationByCode(String reservationCode);

  ReservationResponse confirmReservation(ConfirmReservationRequest confirmReservationRequest);

  ReservationResponse agentConfirmReservation(ConfirmReservationRequest confirmReservationRequest);

  List<PriorDepartureReservationResponse> get24HoursPriorDepartureReservations();
}
