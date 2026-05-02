import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import {
  FaMapMarkerAlt,
  FaBed,
  FaBath,
  FaRulerCombined,
  FaCheckCircle,
  FaStar,
  FaArrowLeft,
  FaHeart,
  FaShare,
  FaCar,
  FaBuilding,
  FaCouch,
  FaSwimmingPool,
  FaStarHalfAlt,
  FaRegStar,
  FaParking,
  FaPhoneAlt,
  FaCalendarAlt,
  FaFileSignature,
  FaChevronLeft,
  FaUserCircle,
  FaQuoteLeft,
} from "react-icons/fa";
import { MdElevator, MdVerified } from "react-icons/md";
import { useAuth } from "../../context/AuthContext";
import { getPropertyById } from "../../services/propertyService";

const AMENITY_CONFIG = [
  { key: "PARKING", label: "Parking", icon: FaParking },
  { key: "ELEVATOR", label: "Elevator", icon: MdElevator },
  { key: "FURNISHED", label: "Furnished", icon: FaCouch },
  { key: "POOL", label: "Pool", icon: FaSwimmingPool },
  { key: "GYM", label: "Gym", icon: FaBuilding },
  { key: "SECURITY", label: "Security", icon: FaCheckCircle },
  { key: "GARDEN", label: "Garden", icon: FaCouch },
  { key: "BALCONY", label: "Balcony", icon: FaBuilding },
  { key: "AIR_CONDITIONING", label: "AC", icon: FaCouch },
  { key: "CENTRAL_HEATING", label: "Heating", icon: FaCouch },
];


const formatPrice = (price) =>
  `${(price || 0).toLocaleString("en-US", { minimumFractionDigits: 0, maximumFractionDigits: 0 })} EGP`;

const renderStars = (score) => {
  const stars = [];
  const s = score || 0;
  const fullStars = Math.floor(s);
  const hasHalf = s - fullStars >= 0.25 && s - fullStars < 0.75;
  const emptyStars = 5 - fullStars - (hasHalf ? 1 : 0);

  for (let i = 0; i < fullStars; i++) stars.push(<FaStar key={`full-${i}`} className="star-icon star-filled" />);
  if (hasHalf) stars.push(<FaStarHalfAlt key="half" className="star-icon star-filled" />);
  for (let i = 0; i < emptyStars; i++) stars.push(<FaRegStar key={`empty-${i}`} className="star-icon star-empty" />);
  return stars;
};

const PropertyDetails = () => {
  const { id } = useParams();
  const { user } = useAuth();
  const [property, setProperty] = useState(null);
  const [activeImageIndex, setActiveImageIndex] = useState(0);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchProperty = async () => {
      try {
        const data = await getPropertyById(id);
        setProperty(data);
      } catch (err) {
        console.error(err);
      } finally {
        setIsLoading(false);
      }
    };
    fetchProperty();
  }, [id]);

  if (isLoading) {
    return (
      <div className="page-wrapper container">
        <div className="loading-state" style={{ padding: "8rem 0", textAlign: "center" }}>
          <div className="spinner" style={{ width: 40, height: 40, margin: "0 auto 1rem" }} />
          <p style={{ color: "var(--color-text-muted)" }}>Loading property details...</p>
        </div>
      </div>
    );
  }

  if (!property) {
    return (
      <div className="page-wrapper container">
        <div className="empty-state">
          <h3 className="empty-state-title">Property Not Found</h3>
          <p className="empty-state-text">
            The property you're looking for doesn't exist or has been removed.
          </p>
          <Link to="/" className="btn btn-primary btn-lg">
            <FaChevronLeft /> Back to Listings
          </Link>
        </div>
      </div>
    );
  }

  // Map backend fields
  const imageUrls = property.imageUrls || [];
  const currentImage = imageUrls[activeImageIndex] || property.mainImageUrl;
  const location = [property.city, property.district].filter(Boolean).join(", ");

  // Amenities from backend are a list of strings like ["PARKING", "POOL"]
  const amenities = property.amenities || [];
  const activeAmenities = AMENITY_CONFIG.filter((a) => amenities.includes(a.key));

  return (
    <div className="page-wrapper">
      <div className="container">
        {/* ── Breadcrumb ── */}
        <nav className="detail-breadcrumb" id="detail-breadcrumb">
          <Link to="/" className="detail-breadcrumb-link">Properties</Link>
          <span className="detail-breadcrumb-separator">/</span>
          <span className="detail-breadcrumb-current">{property.title}</span>
        </nav>

        {/* ══════════ Two-Column Layout ══════════ */}
        <div className="detail-layout">
          {/* ── LEFT COLUMN (70%) ── */}
          <div className="detail-main">
            {/* Image Gallery */}
            <section className="detail-gallery" id="detail-gallery">
              <div className="detail-gallery-main">
                {currentImage ? (
                  <img src={currentImage} alt={property.title} className="detail-gallery-main-image" />
                ) : (
                  <div className="detail-gallery-main-image" style={{
                    background: "var(--color-bg-secondary)",
                    display: "flex", alignItems: "center", justifyContent: "center",
                    minHeight: 300, borderRadius: "var(--radius-lg)", color: "var(--color-text-muted)"
                  }}>No images available</div>
                )}
                <div className="property-card-tags">
                  <span className={`property-card-tag ${property.isAvailable ? 'tag-success' : 'tag-info'}`}>
                    {property.isAvailable && <span className="status-dot"></span>}
                    {property.isAvailable ? 'Available' : 'Unavailable'}
                  </span>
                </div>
              </div>
              {imageUrls.length > 1 && (
                <div className="detail-gallery-thumbnails">
                  {imageUrls.map((url, index) => (
                    <button
                      key={index}
                      className={`detail-gallery-thumb ${activeImageIndex === index ? "is-active" : ""}`}
                      onClick={() => setActiveImageIndex(index)}
                      aria-label={`View image ${index + 1}`}
                    >
                      <img src={url} alt={`${property.title} thumbnail`} className="detail-gallery-thumb-image" loading="lazy" />
                    </button>
                  ))}
                </div>
              )}
            </section>

            {/* Title & Meta */}
            <section className="detail-header" id="detail-header">
              <div className="detail-header-top">
                <h1 className="detail-title">{property.title}</h1>
                <span className="detail-type-badge">{property.type}</span>
              </div>
              <p className="detail-location">
                <FaMapMarkerAlt className="detail-location-icon" /> {location || "Location not specified"}
              </p>
            </section>

            {/* Key Facts */}
            <section className="detail-key-facts" id="detail-key-facts" style={{
              display: "flex", gap: "1.5rem", flexWrap: "wrap", margin: "1.5rem 0",
              padding: "1.25rem", background: "var(--color-bg-secondary)", borderRadius: "var(--radius-lg)"
            }}>
              <div style={{ display: "flex", alignItems: "center", gap: "0.5rem", color: "var(--color-text-secondary)" }}>
                <FaBed /> <span>{property.bedrooms || 0} Beds</span>
              </div>
              <div style={{ display: "flex", alignItems: "center", gap: "0.5rem", color: "var(--color-text-secondary)" }}>
                <FaBath /> <span>{property.bathrooms || 0} Baths</span>
              </div>
              {property.areaSqm && (
                <div style={{ display: "flex", alignItems: "center", gap: "0.5rem", color: "var(--color-text-secondary)" }}>
                  <FaRulerCombined /> <span>{property.areaSqm} m²</span>
                </div>
              )}
              {property.floor != null && (
                <div style={{ display: "flex", alignItems: "center", gap: "0.5rem", color: "var(--color-text-secondary)" }}>
                  <FaBuilding /> <span>Floor {property.floor}</span>
                </div>
              )}
            </section>

            {/* About Section */}
            <section className="detail-about" id="detail-about">
              <h2 className="detail-section-title">About This Property</h2>
              <p className="detail-description">{property.description || "No description provided."}</p>
            </section>

            {/* Amenities Grid */}
            {activeAmenities.length > 0 && (
              <section className="detail-amenities" id="detail-amenities">
                <h2 className="detail-section-title">Amenities</h2>
                <div className="detail-amenities-grid">
                  {activeAmenities.map(({ key, label, icon: Icon }) => (
                    <div key={key} className="detail-amenity-card is-available">
                      <Icon className="detail-amenity-card-icon" />
                      <span className="detail-amenity-card-label">{label}</span>
                      <MdVerified className="detail-amenity-check" />
                    </div>
                  ))}
                </div>
              </section>
            )}

            {/* Reviews Section */}
            <section className="detail-reviews" id="detail-reviews">
              <div className="detail-reviews-header">
                <h2 className="detail-section-title">Reviews</h2>
                <div className="detail-reviews-summary">
                  <div className="detail-reviews-stars">{renderStars(property.averageRating)}</div>
                  <span className="detail-reviews-score">{(property.averageRating || 0).toFixed(1)}</span>
                  <span className="detail-reviews-count">({property.reviewCount || 0} reviews)</span>
                </div>
              </div>

              <div className="detail-reviews-list">
                {property.reviews && property.reviews.length > 0 ? (
                  property.reviews.map((review) => (
                    <article key={review.id} className="detail-review-card">
                      <div className="detail-review-header">
                        <FaUserCircle className="detail-review-avatar-icon" style={{ fontSize: "2.5rem", color: "var(--color-text-muted)" }} />
                        <div className="detail-review-meta">
                          <span className="detail-review-author">{review.tenantFullName}</span>
                          <span className="detail-review-date">{new Date(review.createdAt).toLocaleDateString("en-US", { year: "numeric", month: "long", day: "numeric" })}</span>
                        </div>
                        <div className="detail-review-stars">{renderStars(review.rating)}</div>
                      </div>
                      <div className="detail-review-body">
                        <FaQuoteLeft className="detail-review-quote-icon" />
                        <p className="detail-review-comment">{review.comment}</p>
                      </div>
                    </article>
                  ))
                ) : (
                  <div className="empty-state" style={{ padding: "2rem 0" }}>
                    <FaRegStar style={{ fontSize: "2rem", color: "var(--color-text-muted)", marginBottom: "0.5rem" }} />
                    <p style={{ color: "var(--color-text-muted)" }}>No reviews yet for this property.</p>
                  </div>
                )}
              </div>
            </section>
          </div>

          {/* ── RIGHT COLUMN (30%) — Sticky Booking Card ── */}
          <aside className="detail-sidebar">
            <div className="sticky-booking-card" id="sticky-booking-card">
              <div className="booking-card-price-section">
                <span className="booking-card-price">{formatPrice(property.monthlyRent)}</span>
                <span className="booking-card-period">/ month</span>
              </div>
              <hr className="booking-card-divider" />
              <div className="booking-card-landlord">
                <div className="booking-card-landlord-info">
                  <span className="booking-card-landlord-name">{property.landlordName || "Landlord"}</span>
                  <span className="booking-card-landlord-role"><MdVerified className="booking-card-verified-icon" /> Verified Landlord</span>
                </div>
              </div>
              <hr className="booking-card-divider" />
              <div className="booking-card-actions">
                <Link to={`/book-visit/${property.id}`} className="btn btn-primary btn-lg booking-card-btn" id="book-visit-btn"><FaCalendarAlt /> Book a Visit</Link>
                <Link to={`/apply-rental/${property.id}`} className="btn btn-secondary btn-lg booking-card-btn" id="apply-rental-btn"><FaFileSignature /> Apply for Rental</Link>
              </div>
            </div>
          </aside>
        </div>
      </div>
    </div>
  );
};

export default PropertyDetails;
