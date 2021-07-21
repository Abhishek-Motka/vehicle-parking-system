package com.company.enums;


import java.util.HashSet;
import java.util.Set;

public enum ParkingSlotType
{
    SMALL
            {
                @Override
                public Set<VehicleSize> allowedVehicleSizes()
                {
                    return new HashSet<VehicleSize>() {{
                        add(VehicleSize.SMALL);
                    }};
                }

                @Override
                public VehicleSize bestFitVehicleSize()
                {
                    return VehicleSize.SMALL;
                }
            },
    MEDIUM
            {
                @Override
                public Set<VehicleSize> allowedVehicleSizes()
                {
                    return new HashSet<VehicleSize>() {{
                        addAll(SMALL.allowedVehicleSizes());
                        add(VehicleSize.MEDIUM);
                    }};
                }

                @Override
                public VehicleSize bestFitVehicleSize()
                {
                    return VehicleSize.MEDIUM;
                }
            },
    LARGE
            {
                @Override
                public Set<VehicleSize> allowedVehicleSizes()
                {
                    return new HashSet<VehicleSize>() {{
                        addAll(MEDIUM.allowedVehicleSizes());
                        add(VehicleSize.LARGE);
                    }};
                }

                @Override
                public VehicleSize bestFitVehicleSize()
                {
                    return VehicleSize.LARGE;
                }
            },
    XLARGE
            {
                @Override
                public Set<VehicleSize> allowedVehicleSizes()
                {
                    return new HashSet<VehicleSize>() {{
                        addAll(LARGE.allowedVehicleSizes());
                        add(VehicleSize.XLARGE);
                    }};
                }

                @Override
                public VehicleSize bestFitVehicleSize()
                {
                    return VehicleSize.XLARGE;
                }
            };

    public boolean canFit(VehicleSize vehicleSize) {
        return allowedVehicleSizes().contains(vehicleSize);
    }

    public abstract Set<VehicleSize> allowedVehicleSizes();

    public abstract VehicleSize bestFitVehicleSize();
}
