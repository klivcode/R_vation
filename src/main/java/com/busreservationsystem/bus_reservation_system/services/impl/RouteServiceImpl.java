package com.busreservationsystem.bus_reservation_system.services.impl;

import com.busreservationsystem.bus_reservation_system.dto.request.RouteRequestDTO;
import com.busreservationsystem.bus_reservation_system.dto.response.RouteResponseDTO;
import com.busreservationsystem.bus_reservation_system.entity.Company;
import com.busreservationsystem.bus_reservation_system.entity.Route;
import com.busreservationsystem.bus_reservation_system.exception.ResourceNotFoundException;
import com.busreservationsystem.bus_reservation_system.repository.CompanyRepo;
import com.busreservationsystem.bus_reservation_system.repository.RouteRepo;
import com.busreservationsystem.bus_reservation_system.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepo  routeRepo;
    @Autowired
    private CompanyRepo companyRepo;


    @Override
    public RouteResponseDTO registerRoute(RouteRequestDTO routeRequestDTO) {

        Company company = companyRepo.findById(routeRequestDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));


        Route route = new Route();
        //DTO -> entity
        route.setSource(routeRequestDTO.getSource());
        route.setDestination(routeRequestDTO.getDestination());
        route.setCompany(company);
        if(routeRequestDTO.getSource().equals(routeRequestDTO.getDestination())) {
            throw new IllegalArgumentException("Destination and Source can't be same");
        }
        Route savedRoute = routeRepo.save(route);
        // entity -> DTO
        return new RouteResponseDTO(
                savedRoute.getId(),
                savedRoute.getCreatedAt(),
                savedRoute.getUpdatedAt(),
                savedRoute.getSource(),
                savedRoute.getDestination(),
                savedRoute.getCompany().getId()
        );
    }

    @Override
    public Page<RouteResponseDTO> getAllRoute(Pageable pageable) {
        Page<Route> routes = routeRepo.findAll(pageable);
        return routes.map(
                route -> new RouteResponseDTO(
                        route.getId(),
                        route.getCreatedAt(),
                        route.getUpdatedAt(),
                        route.getSource(),
                        route.getDestination(),
                        route.getCompany().getId()
                )
        );
    }

    @Override
    public RouteResponseDTO getRouteById(Long id) {
        //Validate the id
        Route route = routeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        return new RouteResponseDTO(
                route.getId(),
                route.getCreatedAt(),
                route.getUpdatedAt(),
                route.getSource(),
                route.getDestination(),
                route.getCompany().getId()
        );
    }

    @Override
    public void deleteRouteById(Long id) {
        // validate first the id
        Route route = routeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        routeRepo.delete(route);
    }

    @Override
    public RouteResponseDTO updateRouteById(Long id, RouteRequestDTO routeRequestDto) {
        //Validate the id
        Route route = routeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        route.setSource(routeRequestDto.getSource());
        route.setDestination(routeRequestDto.getDestination());
        Route savedRoute =  routeRepo.save(route);
        return new RouteResponseDTO(
                savedRoute.getId(),
                savedRoute.getCreatedAt(),
                savedRoute.getUpdatedAt(),
                savedRoute.getSource(),
                savedRoute.getDestination(),
                savedRoute.getCompany().getId()
        );
    }


    @Override
    public Page<RouteResponseDTO> searchRoutes(Pageable pageable,String source,
                                               String destination,
                                               Long companyId
                                               ) {

        Specification<Route> spec = (root, query, cb) -> cb.conjunction();

        if (source != null && !source.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(
                            cb.lower(root.get("source")),
                            source.toLowerCase()
                    )
            );
        }

        if (destination != null && !destination.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(
                            cb.lower(root.get("destination")),
                            destination.toLowerCase()
                    )
            );
        }

        if (companyId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("company").get("id"), companyId)
            );
        }

        Page<Route> routes = routeRepo.findAll(spec, pageable);

        return routes.map(route -> new RouteResponseDTO(
                route.getId(),
                route.getCreatedAt(),
                route.getUpdatedAt(),
                route.getSource(),
                route.getDestination(),
                route.getCompany().getId()
        ));
    }

}
